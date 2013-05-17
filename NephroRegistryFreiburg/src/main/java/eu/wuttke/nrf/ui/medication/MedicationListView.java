package eu.wuttke.nrf.ui.medication;

import eu.wuttke.nrf.domain.medication.Medication;
import eu.wuttke.nrf.ui.component.EditableListComposite;
import eu.wuttke.nrf.ui.misc.StringToPrecisionDateConverter;

public class MedicationListView 
extends EditableListComposite<Medication> {
		
	public MedicationListView() {
		super(Medication.class);
	}
	
	@Override
	protected void initTable() {
		getTable().setVisibleColumns(new String[] {"atcCode", "label", "validFrom", "validUntil"});
		getTable().setColumnHeaders(new String[] {"ATC Code", "Name", "Valid From", "Valid Until"});
		getTable().setConverter("validFrom", new StringToPrecisionDateConverter());
		getTable().setConverter("validUntil", new StringToPrecisionDateConverter());
	}

	private static final long serialVersionUID = 1L;

}
