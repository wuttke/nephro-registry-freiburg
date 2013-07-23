package eu.wuttke.nrf.ui.event;

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
		getEditorView().setEventTypes(EventType.findAllEventTypes());
		w.show(((EventListPresenter)getParent()).getListView().getUI(), "Edit Event", "600", "500");	
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

}
