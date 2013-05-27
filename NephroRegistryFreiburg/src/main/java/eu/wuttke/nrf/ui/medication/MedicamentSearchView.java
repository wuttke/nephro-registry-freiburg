package eu.wuttke.nrf.ui.medication;

import eu.wuttke.nrf.domain.medication.Medicament;
import eu.wuttke.nrf.ui.component.SearchListComposite;

public class MedicamentSearchView 
extends SearchListComposite<Medicament> {

	public MedicamentSearchView() {
		super(Medicament.class);
	}
	
	@Override
	protected void initTable() {
		getTable().setVisibleColumns(new String[] {"mid", "tradename", "offerer", "pharmaceuticalForm" });
		getTable().setColumnHeaders(new String[] {"MID", "Tradename", "Offerer", "Pharmaceutical Form" });
	}

	private static final long serialVersionUID = 1L;
	
}
