package eu.wuttke.nrf.ui.event;

import eu.wuttke.nrf.domain.event.Event;
import eu.wuttke.nrf.ui.component.EditableListComposite;
import eu.wuttke.nrf.ui.misc.StringToPrecisionDateConverter;

public class EventListView 
extends EditableListComposite<Event> {
		
	public EventListView() {
		super(eu.wuttke.nrf.domain.event.Event.class);
	}
	
	@Override
	protected void initTable() {
		getTable().setVisibleColumns(new String[] {"type", "eventStartDate", "eventEndDate"});
		getTable().setColumnHeaders(new String[] {"Event Type", "Date", "End"});
		getTable().setConverter("eventStartDate", new StringToPrecisionDateConverter());
		getTable().setConverter("eventEndDate", new StringToPrecisionDateConverter());
		getTable().setSortContainerPropertyId("eventStartDate");
	}
		
	private static final long serialVersionUID = 1L;

}
