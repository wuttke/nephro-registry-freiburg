package eu.wuttke.nrf.ui.event;

import java.util.List;

import com.vaadin.ui.UI;

import eu.wuttke.nrf.domain.event.Event;
import eu.wuttke.nrf.domain.event.EventType;
import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.ui.component.OkCancelWindow;
import eu.wuttke.nrf.ui.presenter.EditorPresenter;
import eu.wuttke.nrf.ui.presenter.RefreshablePresenter;

public class EventEditorPresenter 
extends EditorPresenter<Event, EventEditorView> {
	
	private Subject parentSubject;
	
	public EventEditorPresenter(Subject parentSubject, RefreshablePresenter parentPresenter) {
		super(new EventEditorView(), parentPresenter);
		this.parentSubject = parentSubject;
	}
	
	@Override
	public void showEditorWindow(OkCancelWindow w) {
		List<EventType> types = EventType.findAllEventTypes();
		if (getEntity().getType() != null) {
			getEntity().setType(findEventType(types, getEntity().getType().getId()));
		}
		getEditorView().setEventTypes(types);
		w.show(findUI(), "Edit Event", "600", "500");	
	}

	private EventType findEventType(List<EventType> types, long id) {
		for (EventType type : types)
			if (type.getId().longValue() == id)
				return type;
		return null;
	}

	@Override
	public UI findUI() {
		return ((EventListPresenter)getParent()).getListView().getUI();
	}
	
	@Override
	public Event createEntity() {
		Event d = new Event();
		d.setSubject(parentSubject);
		return d;
	}
	
	@Override
	public void saveEntity(Event entity) {
		entity.merge();
	}
	
	@Override
	public void realDeleteEntity(Event entity) {
		entity.remove();
	}

}
