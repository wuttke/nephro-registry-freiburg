package eu.wuttke.nrf.ui.visit;

import com.vaadin.ui.Button.ClickListener;

import eu.wuttke.nrf.domain.visit.Visit;
import eu.wuttke.nrf.ui.component.EditableListComposite;
import eu.wuttke.nrf.ui.misc.StringToDateTimeConverter;

public class VisitListView 
extends EditableListComposite<Visit> {
		
	public VisitListView() {
		super(Visit.class);
	}
	
	public void addSyncAction(ClickListener listener) {
		addButton("KIS Sync", listener, false);
	}
	
	@Override
	protected void initTable() {
		getTable().setVisibleColumns(new String[] {"visitDateTime", "label"});
		getTable().setColumnHeaders(new String[] {"Visit Date/Time", "Label"});
		getTable().setConverter("visitDateTime", new StringToDateTimeConverter());
		getTable().setSortContainerPropertyId("visitDateTime");
	}
		
	private static final long serialVersionUID = 1L;

}
