package eu.wuttke.nrf.ui.encounter;

import eu.wuttke.nrf.domain.encounter.Encounter;
import eu.wuttke.nrf.ui.component.EditableListComposite;
import eu.wuttke.nrf.ui.misc.StringToShortDateConverter;

public class EncounterListView 
extends EditableListComposite<Encounter> {
		
	public EncounterListView() {
		super(Encounter.class);
	}
	
	/*
	public void addSyncAction(ClickListener listener) {
		addButton("KIS Sync", listener, false);
	}
	*/
	
	@Override
	protected void initTable() {
		getTable().setVisibleColumns(new String[] {"encounterDate", "type", "label"});
		getTable().setColumnHeaders(new String[] {"Encounter Date", "Type", "Label"});
		getTable().setConverter("encounterDate", new StringToShortDateConverter());
		getTable().setSortContainerPropertyId("encounterDate");
	}
		
	private static final long serialVersionUID = 1L;

}
