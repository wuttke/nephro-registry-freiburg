package eu.wuttke.nrf.interf.kis;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.meona.infrastructure.patient.HistoryPatientIdentifier;
import de.meona.infrastructure.patient.StayType;

import eu.wuttke.nrf.domain.encounter.Encounter;
import eu.wuttke.nrf.domain.encounter.EncounterType;
import eu.wuttke.nrf.domain.subject.Subject;

@Component
public class KisVisitImporter {

	public List<Encounter> retrieveKisVisits(long patientId) {
		HistoryPatientIdentifier[] hpis = meonaInfrastructureService.getKisPatientHistory(patientId, StayType.OUTPATIENT);
		if (hpis == null)
			return new LinkedList<Encounter>();
		
		List<Encounter> results = new ArrayList<Encounter>(hpis.length);
		for (HistoryPatientIdentifier hpi : hpis) {
			Encounter v = new Encounter();
			v.setLabel(hpi.getWardName());
			v.setEncounterDate(hpi.getStayBegin());
			v.setType(EncounterType.OUTPATIENT_VISIT);
			results.add(v);
		}
		
		return results;
	}
	
	public List<Encounter> mergeKisVisits(Subject s) {
		List<Encounter> kisVisits = retrieveKisVisits(Long.parseLong(s.getPatientId()));
		List<Encounter> myVisits = Encounter.findEncountersBySubject(s).getResultList();
		List<Encounter> results = new ArrayList<Encounter>(kisVisits.size());
		
		for (Encounter kisVisit : kisVisits) {
			if (!haveVisitWithDate(myVisits, kisVisit.getEncounterDate())) {
				kisVisit.setSubject(s);
				kisVisit.merge();
				results.add(kisVisit);
			}
		}
		
		return results;
	}
	
	private boolean haveVisitWithDate(List<Encounter> myVisits, Date visitDateTime) {
		for (Encounter visit : myVisits)
			if (DateUtils.truncatedEquals(visit.getEncounterDate(), visitDateTime, Calendar.DATE))
				return true;
		return false;
	}

	public void setMeonaInfrastructureService(
			MeonaInfrastructure meonaInfrastructureService) {
		this.meonaInfrastructureService = meonaInfrastructureService;
	}
	
	@Autowired
	private MeonaInfrastructure meonaInfrastructureService;
	
}
