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

import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.domain.visit.Visit;

@Component
public class KisVisitImporter {

	public List<Visit> retrieveKisVisits(long patientId) {
		HistoryPatientIdentifier[] hpis = meonaInfrastructureService.getKisPatientHistory(patientId, StayType.OUTPATIENT);
		if (hpis == null)
			return new LinkedList<Visit>();
		
		List<Visit> results = new ArrayList<Visit>(hpis.length);
		for (HistoryPatientIdentifier hpi : hpis) {
			Visit v = new Visit();
			v.setLabel(hpi.getWardName());
			v.setVisitDateTime(hpi.getStayBegin());
			results.add(v);
		}
		
		return results;
	}
	
	public List<Visit> mergeKisVisits(Subject s) {
		List<Visit> kisVisits = retrieveKisVisits(Long.parseLong(s.getPatientId()));
		List<Visit> myVisits = Visit.findVisitsBySubject(s).getResultList();
		List<Visit> results = new ArrayList<Visit>(kisVisits.size());
		
		for (Visit kisVisit : kisVisits) {
			if (!haveVisitWithDate(myVisits, kisVisit.getVisitDateTime())) {
				kisVisit.setSubject(s);
				kisVisit.merge();
				results.add(kisVisit);
			}
		}
		
		return results;
	}
	
	private boolean haveVisitWithDate(List<Visit> myVisits, Date visitDateTime) {
		for (Visit visit : myVisits)
			if (DateUtils.truncatedEquals(visit.getVisitDateTime(), visitDateTime, Calendar.MINUTE))
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
