package eu.wuttke.nrf.ui.encounter;

import com.vaadin.ui.Button.ClickListener;

import eu.wuttke.nrf.domain.encounter.Encounter;
import eu.wuttke.nrf.ui.component.EditableListComposite;
import eu.wuttke.nrf.ui.misc.StringToDateTimeConverter;

public class EncounterListView 
extends EditableListComposite<Encounter> {
		
	public EncounterListView() {
		super(Encounter.class);
	}
	
	public void addSyncAction(ClickListener listener) {
		addButton("KIS Sync", listener, false);
	}
	
	@Override
	protected void initTable() {
		getTable().setVisibleColumns(new String[] {"encounterDateTime", "type", "label"});
		getTable().setColumnHeaders(new String[] {"Encounter Date/Time", "Type", "Label"});
		getTable().setConverter("encounterDateTime", new StringToDateTimeConverter());
		getTable().setSortContainerPropertyId("encounterDateTime");
	}
		
	private static final long serialVersionUID = 1L;

}
