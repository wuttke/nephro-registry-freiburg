package eu.wuttke.nrf.interf.kis;

import java.util.Date;

import de.meona.infrastructure.lab.LabRow;
import de.meona.infrastructure.lab.LabRowType;
import de.meona.infrastructure.patient.HistoryPatientIdentifier;
import de.meona.infrastructure.patient.StayType;

public interface MeonaInfrastructure {

	public HistoryPatientIdentifier[] getKisPatientHistory(long patientId,
			StayType stayType);

	public LabRow[] fetchLabValues(long patientId, Date startDate, Date endDate, LabRowType[] labValueCodes, boolean includePoct);

}
