package eu.wuttke.nrf.importer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.stereotype.Component;
import org.supercsv.io.CsvListReader;
import org.supercsv.prefs.CsvPreference;

import eu.wuttke.nrf.domain.attribute.AttributeCategory;
import eu.wuttke.nrf.domain.attribute.AttributeDao;
import eu.wuttke.nrf.domain.attribute.AttributeType;
import eu.wuttke.nrf.domain.diagnosis.Diagnosis;
import eu.wuttke.nrf.domain.diagnosis.DiagnosisCodingSystem;
import eu.wuttke.nrf.domain.event.Event;
import eu.wuttke.nrf.domain.event.EventType;
import eu.wuttke.nrf.domain.misc.PrecisionDate;
import eu.wuttke.nrf.domain.misc.PrecisionDateType;
import eu.wuttke.nrf.domain.misc.PrecisionDateUtil;
import eu.wuttke.nrf.domain.subject.Gender;
import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.domain.subject.SubjectAttribute;
import eu.wuttke.nrf.domain.subject.SubjectCategory;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Configurable
@RooJavaBean
public class ImportCystRegistryFile {

	public static void main(String[] args) 
	throws IOException {
		ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("META-INF/spring/applicationContext.xml");
		app.getBean(ImportCystRegistryFile.class).doImport("C:\\Zysten.csv");
		app.close();
	}
	
	private List<String> header;
	private List<String> line;
	private int lineCounter;
	private Subject patient;
	
	
	public void doImport(String fileName)
	throws IOException {
		CsvListReader csvReader = new CsvListReader(
				new InputStreamReader(
						new FileInputStream(fileName), "ISO-8859-1"), CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);
		header = csvReader.read(); 

		lineCounter = 0;
		while ((line = csvReader.read()) != null) {
			processCsvLine();
			lineCounter++;
		}
		
		csvReader.close();
	}

	private void processCsvLine() {
		patient = findPatient();
		if (patient == null) {
			patient = createPatient();
			if (patient == null)
				return; // next line
			
			logger.info("create new patient: {}", patient.formatFullName());
			createSubjectAttributeIfNonExistent(CystAttributeShortcut.BEZUG_NEUMANNREGISTER_ADPKD, Integer.toString(lineCounter + 1));
		} else {
			logger.info("update patient: {}", patient.formatFullName());
			checkName(patient.getLastName());
		}
		
		createOrUpdatePatientAttributes();
	}
			
	private void createOrUpdatePatientAttributes() {
		doSubjectCategory();
		doMutation();
		doSimpleFlags();
		doDialysis();
		doSimpleStrings();
		doConcatString();
		doDates();
		doIndex();
		doNichtanschreiben();
	}
	
	private void doNichtanschreiben() {
		String cv1 = (findColumnValue("Pat. nicht anschreiben!!!9"));
		String cv2 = (findColumnValue("Pat. nicht anschreiben!!!"));
		if (cv1 != null && cv1.toUpperCase().contains("NICHT ANSCHREIBEN") ||
			cv2 != null && cv2.toUpperCase().contains("NICHT ANSCHREIBEN"))
			createSubjectAttributeIfNonExistent(CystAttributeShortcut.NICHT_ANSCHREIBEN, "TRUE");
	}

	private void doIndex() {
		String cv = findColumnValue("Index");
		if (cv != null && cv.toUpperCase().contains("INDEX"))
			createSubjectAttributeIfNonExistent(CystAttributeShortcut.INDEXFALL, "TRUE");
	}

	private void doSubjectCategory() {
		doSubjectCategory(1);
		doSubjectCategory(3);
	}
	
	private void doSubjectCategory(long catId) {
		AttributeCategory category = AttributeCategory.findAttributeCategory(catId);
		if (SubjectCategory.findSubjectCategorysBySubjectAndCategory(patient, category).getResultList().size() == 0) {
			SubjectCategory sc = new SubjectCategory();
			sc.setSubject(patient);
			sc.setCategory(category);
			sc.persist();
			logger.info("subject category {} created", catId);
		} else
			logger.info("subject category {} present", catId);
	}

	private void doDates() {
		// -> Events
		doDate(CystAttributeShortcut.ERSTMANIFESTATION_DATUM, "Erste Symptome", null);
		doDate(CystAttributeShortcut.ARTERIELLE_HYPERTONIE_SEIT_DATUM, "Hypertonie", "seit:");
		doDate(CystAttributeShortcut.NEPHREKTOMIE_LINKS_DATUM, "Nephrek.", "li.");
		doDate(CystAttributeShortcut.NEPHREKTOMIE_RECHTS_DATUM, "Nephrek.", "re.");
		doDate(CystAttributeShortcut.NIERENTRANSPLANTATION_DATUM, "NTx", null);
		doDate(CystAttributeShortcut.EINGABEDATUM, "Eing.", "Datum");
		doDate(CystAttributeShortcut.FRAGEBOGEN_AUSGANG, "Frage-bogen", "Ausgang");
		doDate(CystAttributeShortcut.FRAGEBOGEN_EINGANG, "Frage-bogen", "Eingang");
		doDate(CystAttributeShortcut.ALTER_BEI_ERFASSUNG, "Alter", "Erfas"); //??
	}

	private void doDate(CystAttributeShortcut cas, String str1,
			String str2) {
		String cv = findColumnValue(str1, str2);
		if (!StringUtils.isEmpty(cv) &&
			StringUtils.isNumeric(cv) &&
			cv.length() == 4)
			createSubjectAttributeIfNonExistent(cas, cv);
	}

	private void doConcatString() {
		StringBuilder str = new StringBuilder();
		
		String cv1 = findColumnValue("Pat. nicht anschreiben!!!");
		if (!StringUtils.isEmpty(cv1))
			add(str, cv1);

		String cv2 = findColumnValue("Bemerkungen");
		if (!StringUtils.isEmpty(cv2))
			add(str, cv2);

		String cv3 = findColumnValue("Pat. nicht anschreiben!!!9");
		if (!StringUtils.isEmpty(cv3))
			add(str, cv3);

		if (str.length() > 0)
			createSubjectAttributeIfNonExistent(CystAttributeShortcut.DIVERSE_BEMERKUNGEN, str.toString());
	}

	private void add(StringBuilder str, String cv1) {
		if (str.length() > 0)
			str.append(", ");
		str.append(cv1);
	}

	private void doSimpleStrings() {
		doSimpleString(CystAttributeShortcut.STRASSE, "Strasse");
		doSimpleString(CystAttributeShortcut.PLZ, "PLZ");
		doSimpleString(CystAttributeShortcut.ORT, "Ort");
		doSimpleString(CystAttributeShortcut.LAND, "Land");
		doSimpleString(CystAttributeShortcut.TELEFON, "Tel. / Fax / E-Mail"); // Gemischt!
		doSimpleString(CystAttributeShortcut.NACHNAME_HAUSARZT, "Nachname", "Hausarzt");
		doSimpleString(CystAttributeShortcut.ORT_HAUSARZT, "Ort7");
		doSimpleString(CystAttributeShortcut.NACHNAME_NEPHROLOGE, "Nachname Nephr.");
		doSimpleString(CystAttributeShortcut.ORT_NEPHROLOGE, "Ort4");
		doSimpleString(CystAttributeShortcut.VERWANDTE_BEMERKUNG, "Index");
		doSimpleString(CystAttributeShortcut.FAMILIENNAME, "Familie");
		doSimpleString(CystAttributeShortcut.BETROFFENE_VERWANDTE_ZAHLEN, "Betroffene Verwandte:", "Eltern");
		doSimpleString(CystAttributeShortcut.BETROFFENE_VERWANDTE_TEXT, "Bemerkungen und Betroffene");
		doSimpleString(CystAttributeShortcut.NUMMER_ERSTE_PROBE, "Pr.-Nr.", "1. Pr.");
		doSimpleString(CystAttributeShortcut.NUMMER_ZWEITE_PROBE, "2.Pr.");
	}

	private void doSimpleString(CystAttributeShortcut cas,
			String str) {
		doSimpleString(cas, str, null);
	}

	private void doSimpleString(CystAttributeShortcut cas, String str1,
			String str2) {
		String cv = findColumnValue(str1, str2);
		if (StringUtils.isNotEmpty(cv))
			createSubjectAttributeIfNonExistent(cas, cv);
	}

	private void doSimpleFlags() {
		doSimpleFlag(CystAttributeShortcut.CONSENT_JANEIN, "Consent");
		doSimpleFlag(CystAttributeShortcut.FAMILIAERE_ZYSTENNIEREN, "Familiär"); //XXX
		doSimpleFlag(CystAttributeShortcut.ZYSTENNIERE_RECHTS, "ZyNr.", "rechts");
		doSimpleFlag(CystAttributeShortcut.ZYSTENNIERE_LINKS, "ZyNr.", "links");
		doSimpleFlag(CystAttributeShortcut.LEBERZYSTEN, "Leber-zysten");
		doSimpleFlag(CystAttributeShortcut.ZYSTISCHE_LEBERERKRANKUNG_PLD, "PLD", "Polycystic liver disease");
		doSimpleFlag(CystAttributeShortcut.PANKREASZYSTEN, "Pankreas-zysten");
		doSimpleFlag(CystAttributeShortcut.MILZZYSTEN, "Milz-zysten");
		doSimpleFlag(CystAttributeShortcut.NIERENKARZINOM, "Nieren-karzinom");
		doSimpleFlag(CystAttributeShortcut.ANEURYSMA, "Aneu-rysma");
		doSimpleFlag(CystAttributeShortcut.HERZKLAPPENFEHLER, "Herzklappen-fehler");
		doSimpleFlag(CystAttributeShortcut.HARNWEGSINFEKTE, "Harnwegs-infekte");
		doSimpleFlag(CystAttributeShortcut.ANAEMIE, "Anämie");
		doSimpleFlag(CystAttributeShortcut.SIGMADIVERTIKULOSE, "Sigmadivertikulose");
		doSimpleFlag(CystAttributeShortcut.ZN_NEPHREKTOMIE_LINKS, "Nephrek-", "li.");
		doSimpleFlag(CystAttributeShortcut.ZN_NEPHREKTOMIE_RECHTS, "Nephrek-", "re.");
		doSimpleFlag(CystAttributeShortcut.HAUSARZT_SOLL_INFORMIERT_WERDEN, "Hausarzt soll über Ergebnis informiert werden");
		doSimpleFlag(CystAttributeShortcut.ZEREBROVASKULAERER_INSULT, "Insult");
		doSimpleFlag(CystAttributeShortcut.NIERENTRANSPLANTATION, "NTx");
		doSimpleFlag(CystAttributeShortcut.ARTERIELLE_HYPERTONIE, "Hypertonie");

		//-> Events
		doEvent("NEPHR_LI", "Nephrek-", "li.");
		doEvent("NTX", "NTx", null);
		doEvent("NEPHR_RE", "Nephrek-", "re.");
		
		doDiagnosis("Q61", "Familiäre Zystennieren", "Familiär");
		doDiagnosis("D37.6", "Leberzysten", "Leber-zysten");
		doDiagnosis("Q44.6", "Zystische Lebererkrankung", "PLD", "Polycystic liver disease");
		doDiagnosis("K86.2", "Pankreaszysten", "Pankreas-zysten");
		doDiagnosis("D73.4", "Milzzysten", "Milz-zysten");
		doDiagnosis("C64", "Nierenkarzinom", "Nieren-karzinom");
		doDiagnosis("I67.1", "Zerebrales Aneurysma", "Aneu-rysma");
		doDiagnosis("I08", "Herzklappenfehler", "Herzklappen-fehler");
		doDiagnosis("N39.0", "Harnwegsinfeke", "Harnwegs-infekte");
		doDiagnosis("D63", "Anämie", "Anämie");
		doDiagnosis("K57", "Sigmadivertikulose", "Sigmadivertikulose");
		doDiagnosis("I63", "Zerebrovaskulärer Insult", "Insult");
		doDiagnosis("I10", "Arterielle Hypertonie", "Hypertonie");
	}
	
	private void doEvent(String code, String pattern1, String pattern2) {
		String cv = findColumnValue(pattern1, pattern2);
		if (cv != null && cv.equals("1"))
			createEvent(code, null);
		else if (cv != null && cv.length() == 4 && StringUtils.isNumeric(cv)) // Jahreszahl
			createEvent(code, cv);
	}

	private void createEvent(String code, String year) {
		Event evt = new Event();
		evt.setSubject(patient);
		evt.setType(EventType.findEventTypesByCodeEquals(code).getSingleResult());
		if (year != null)
			evt.setEventStartDate(PrecisionDateUtil.parsePrecisionDate(year, PrecisionDateType.YEAR));
		evt.persist();
	}

	private void doDiagnosis(String code, String label, String pattern1) {
		doDiagnosis(code, label, pattern1, null);
	}
	
	private void doDiagnosis(String code, String label, String pattern1, String pattern2) {
		String cv = findColumnValue(pattern1, pattern2);
		if (cv != null && cv.equals("1"))
			createDiagnosis(code, label, null, false);
		else if (cv != null && cv.equals("0"))
			createDiagnosis(code, MessageFormat.format("Ausschluss {0}", label), null, true);
		else if (cv != null && cv.length() == 4 && StringUtils.isNumeric(cv)) // Jahreszahl
			createDiagnosis(code, label, cv, false);
	}

	private void createDiagnosis(String code, String label, String year, boolean exclusion) {
		Diagnosis d = new Diagnosis();
		d.setCode(code);
		d.setCodingSystem(DiagnosisCodingSystem.ICD10);
		d.setLabel(label);
		d.setSubject(patient);
		if (year != null)
			d.setValidFrom(PrecisionDateUtil.parsePrecisionDate(year, PrecisionDateType.YEAR));
		d.setExclusion(exclusion);
		d.persist();
	}

	private void doDialysis() {
		String hd = findColumnValue("Alter bei Dialyse-beginn");
		if (hd != null && hd.length() > 0 && !hd.equals("0")) {
			createSubjectAttributeIfNonExistent(CystAttributeShortcut.DIALYSE, "TRUE");
			
			if (hd.length() == 2 && StringUtils.isNumeric(hd)) {
				int age = Integer.parseInt(hd);
				
				Calendar c = Calendar.getInstance();
				c.setTime(patient.getBirthdate());
				c.add(Calendar.YEAR, age);
				
				createSubjectAttributeIfNonExistent(CystAttributeShortcut.DIALYSEBEGINN_DATUM, Integer.toString(c.get(Calendar.YEAR)));
				createEvent("DIALYSIS", Integer.toString(c.get(Calendar.YEAR)));
			}
		} else if (hd != null && hd.length() > 0 && hd.equals("0"))
			createSubjectAttributeIfNonExistent(CystAttributeShortcut.DIALYSE, "FALSE");
	}

	private void doSimpleFlag(CystAttributeShortcut sc, String str1, String str2) {
		String cv = findColumnValue(str1, str2);
		if (cv != null && cv.equals("1"))
			createSubjectAttributeIfNonExistent(sc, "TRUE");
		else if (cv != null && cv.equals("0"))
			createSubjectAttributeIfNonExistent(sc, "FALSE");
		else if (cv != null && cv.length() == 4 && StringUtils.isNumeric(cv)) // Jahreszahl
			createSubjectAttributeIfNonExistent(sc, "TRUE");
	}

	private void doSimpleFlag(CystAttributeShortcut sc, String str) {
		doSimpleFlag(sc, str, null);
	}

	private void doMutation() {
		String mu = findColumnValue("MUTATION");
		String p1n = findColumnValue("PKD1neg.");
		String p2n = findColumnValue("PK2 neg.");
		
		if (!StringUtils.isEmpty(mu)) {
			if (mu.contains("PKD1")) {
				createSubjectAttributeIfNonExistent(CystAttributeShortcut.PKD1MUTATION_VORHANDEN, "TRUE");
				createSubjectAttributeIfNonExistent(CystAttributeShortcut.PKD2MUTATION_VORHANDEN, "FALSE");
			} else if (mu.contains("PKD2")) {
				createSubjectAttributeIfNonExistent(CystAttributeShortcut.PKD1MUTATION_VORHANDEN, "FALSE");
				createSubjectAttributeIfNonExistent(CystAttributeShortcut.PKD2MUTATION_VORHANDEN, "TRUE");
			} else {
				if ((p1n != null && p1n.equals("1") && p2n != null && p2n.equals("1")) ||
						mu.equals("---")) {
					createSubjectAttributeIfNonExistent(CystAttributeShortcut.KEINE_MUTATION_GEFUNDEN, "TRUE");
					createSubjectAttributeIfNonExistent(CystAttributeShortcut.PKD1MUTATION_VORHANDEN, "FALSE");
					createSubjectAttributeIfNonExistent(CystAttributeShortcut.PKD2MUTATION_VORHANDEN, "FALSE");
				}
			}
			createSubjectAttributeIfNonExistent(CystAttributeShortcut.BEZEICHNUNG_MUTATION, mu);
		} else {
			if (p2n != null && p2n.equals("1"))
				createSubjectAttributeIfNonExistent(CystAttributeShortcut.PKD2MUTATION_VORHANDEN, "FALSE");
			if (p1n != null && p1n.equals("1"))
				createSubjectAttributeIfNonExistent(CystAttributeShortcut.PKD1MUTATION_VORHANDEN, "FALSE");
		}
	}

	private void doDeath() {
		doDeath(findColumnValue("Pat. nicht anschreiben!!!9"));
		doDeath(findColumnValue("Pat. nicht anschreiben!!!"));
	}

	private void doDeath(String cv) {
		if (StringUtils.isEmpty(cv))
			return;
		if (cv.contains("verst.") ||
			cv.contains("verstorben")) {
			//createSubjectAttributeIfNonExistent(CystAttributeShortcut.VERSTORBEN_JANEIN, "TRUE");
			patient.setDeath(true);
			
			String y = null;
			PrecisionDate pd = null;
			String[] words = cv.split(" ");
			for (String w : words) {
				String w1 = w.trim();
				if (w1.length() == 4 && StringUtils.isNumeric(w1)) // Jahr
					y = w1;
				else if (pd == null) {
					try {
						pd = PrecisionDateUtil.parsePrecisionDate(w1);
						if (pd.getDate() == null)
							pd = null;
					} catch (NumberFormatException nfe) {
					}
				}
			}
			if (pd != null) {
				patient.setDateOfDeath(pd);
				//createSubjectAttributeIfNonExistent(CystAttributeShortcut.TODESDATUM, PrecisionDateUtil.formatPrecisionDate(pd));
			} else if (y != null) {
				patient.setDateOfDeath(PrecisionDateUtil.parsePrecisionDate(y, PrecisionDateType.YEAR));
				//createSubjectAttributeIfNonExistent(CystAttributeShortcut.TODESDATUM, y);
			}
		}
	}

	private void checkName(String lastName) {
		String cv = findColumnValue("Name");
		if (!cv.contains(lastName))
			throw new RuntimeException(CystAttributeShortcut.BEZUG_NEUMANNREGISTER_ADPKD + " changed"); //$NON-NLS-1$
	}

	private void createSubjectAttributeIfNonExistent(CystAttributeShortcut attributeType, String attributeValue) {
		AttributeType type = getAttributeTypeByShortcut(attributeType.toString());
		
		if (!attributeDao.subjectHasAttributeWithType(patient, type)) {
			SubjectAttribute sa = new SubjectAttribute();
			sa.setAttributeType(type);
			sa.setAttributeValue(attributeValue);
			sa.setSubject(patient);
			sa.persist();
		}
	}

	private Subject createPatient() {
		patient = new Subject();
		patient.setFirstName(findColumnValue("Vorname"));
		patient.setLastName(findColumnValue("Name"));
		
		if (StringUtils.isEmpty(patient.getFirstName()) ||
			StringUtils.isEmpty(patient.getLastName())) {
			logger.error("skip line {} - no first name / last name");
			return null;
		}
		
		if (patient.getLastName().contains("geb.")) {
			int idx = patient.getLastName().indexOf("geb.");
			String lastName = patient.getLastName().substring(0, idx).trim();
			String birthName = patient.getLastName().substring(idx + 4).trim();
			if (lastName.endsWith(","))
				lastName = lastName.substring(0, lastName.length() - 1).trim();
			patient.setLastName(lastName);
			patient.setBirthName(birthName);
		}
			
		
		patient.setBirthdate(tryParseBirthdate());
		patient.setGender(tryFindGender());
		patient.setPatientId(tryFindPatientId());
		patient.generatePseudonym();
		patient.setTitle("");
		
		doDeath();

		patient.persist();
		return patient;
	}

	private String tryFindPatientId() {
		String patientId = findColumnValue("PIZ");
		if (patientId == null)
			patientId = "";
		return patientId;
	}

	private Gender tryFindGender() {
		String anr = findColumnValue("Anrede");
		if (anr != null) {
			if (anr.toUpperCase().contains("FRAU"))
				return Gender.FEMALE;
			else if (anr.toUpperCase().contains("HERR"))
				return Gender.MALE;
		}
		
		logger.error("unable to determine gender for row {}, anrede {}", lineCounter, anr);
		return Gender.UNKNOWN;
	}

	private Date tryParseBirthdate() {
		Date blank = PrecisionDateUtil.parseIsoDate("1901-01-01");
		
		String bd = findColumnValue("Geb.dat.");
		if (StringUtils.isEmpty(bd)) {
			logger.error("unable to determine birthdate for row {}", lineCounter);
			return blank;
		}
		
		PrecisionDate pd = PrecisionDateUtil.parsePrecisionDate(bd);
		if (pd == null || pd.getDate() == null) {
			logger.error("invalid birthdate for row {}", lineCounter);
			return blank;
		}
		
		return pd.getDate();
	}

	private String findColumnValue(String columnTitle) {
		for (int i = 0; i < header.size(); i++)
			if (header.get(i).toUpperCase().trim().equals(columnTitle.toUpperCase()))
				if (line.size() > i && line.get(i) != null)
					return line.get(i).trim();
		
		for (int i = 0; i < header.size(); i++)
			if (header.get(i).toUpperCase().contains(columnTitle.toUpperCase()))
				if (line.size() > i && line.get(i) != null)
					return line.get(i).trim();
		
		return null;
	}

	private String findColumnValue(String columnTitle1, String columnTitle2) {
		if (columnTitle2 == null)
			return findColumnValue(columnTitle1);
		
		for (int i = 0; i < header.size(); i++)
			if (header.get(i).toUpperCase().contains(columnTitle1.toUpperCase()) &&
				header.get(i).toUpperCase().contains(columnTitle2.toUpperCase()))
				if (line.size() > i && line.get(i) != null)
					return line.get(i).trim();
		
		return null;
	}
	
	private Subject findPatient() {
		TypedQuery<SubjectAttribute> q = SubjectAttribute.entityManager().createQuery("FROM SubjectAttribute WHERE attributeType = :type AND attributeValue = :val", SubjectAttribute.class);
		q.setParameter("type", getAttributeTypeByShortcut(CystAttributeShortcut.BEZUG_NEUMANNREGISTER_ADPKD.toString()));
		q.setParameter("val", Integer.toString(lineCounter + 1));
		
		List<SubjectAttribute> a = q.getResultList();
		if (a.size() > 0)
			return a.get(0).getSubject();
		else
			return null;
	}
	
	private AttributeType getAttributeTypeByShortcut(String shortcut) {
		return AttributeType.findAttributeTypesByShortcutEquals(shortcut).getSingleResult();
	}
	
	@Autowired
	private AttributeDao attributeDao;

	private static Logger logger = LoggerFactory.getLogger(ImportCystRegistryFile.class);
	
}
