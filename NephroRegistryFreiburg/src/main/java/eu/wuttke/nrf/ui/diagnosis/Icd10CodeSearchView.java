package eu.wuttke.nrf.ui.diagnosis;

import eu.wuttke.nrf.domain.diagnosis.Icd10Code;
import eu.wuttke.nrf.ui.component.SearchListComposite;

public class Icd10CodeSearchView 
extends SearchListComposite<Icd10Code> {

	public Icd10CodeSearchView() {
		super(Icd10Code.class);
	}
	
	@Override
	protected void initTable() {
		getTable().setVisibleColumns(new String[] {"code", "name" });
		getTable().setColumnHeaders(new String[] {"Code", "Name" });
	}

	private static final long serialVersionUID = 1L;
	
}
