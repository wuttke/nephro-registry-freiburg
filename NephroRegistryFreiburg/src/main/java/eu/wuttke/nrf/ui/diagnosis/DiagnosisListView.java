package eu.wuttke.nrf.ui.diagnosis;

import eu.wuttke.nrf.domain.diagnosis.Diagnosis;
import eu.wuttke.nrf.ui.component.EditableListComposite;
import eu.wuttke.nrf.ui.misc.StringToPrecisionDateConverter;

public class DiagnosisListView 
extends EditableListComposite<Diagnosis> {
		
	public DiagnosisListView() {
		super(Diagnosis.class);
	}
	
	@Override
	protected void initTable() {
		getTable().setVisibleColumns(new String[] {"code", "label", "validFrom", "validUntil"});
		getTable().setColumnHeaders(new String[] {"Code", "Label", "Valid From", "Valid Until"});
		getTable().setConverter("validFrom", new StringToPrecisionDateConverter());
		getTable().setConverter("validUntil", new StringToPrecisionDateConverter());
	}
		
	private static final long serialVersionUID = 1L;

}
