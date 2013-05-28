package eu.wuttke.nrf.interf.kis;

import de.meona.infrastructure.patient.HistoryPatientIdentifier;
import de.meona.infrastructure.patient.StayType;

public interface MeonaInfrastructure {

	public HistoryPatientIdentifier[] getKisPatientHistory(long patientId,
			StayType stayType);

}
