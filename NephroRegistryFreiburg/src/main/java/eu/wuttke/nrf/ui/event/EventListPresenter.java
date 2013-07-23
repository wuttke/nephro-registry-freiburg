package eu.wuttke.nrf.ui.event;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Configurable;

import eu.wuttke.nrf.domain.event.Event;
import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.ui.presenter.EditorPresenter;
import eu.wuttke.nrf.ui.presenter.ListPresenter;

@Configurable
public class EventListPresenter 
extends ListPresenter<Event, EventListView> {
	
	private Subject parentSubject;
	
	public EventListPresenter() {
		super(new EventListView());
	}
	
	public void setParentSubject(Subject parentSubject) {
		this.parentSubject = parentSubject;
	}
	
	@Override
	public Collection<Event> loadEntities() {
		return Event.findEventsBySubject(parentSubject).getResultList();
	}
	
	@Override
	public EditorPresenter<Event, ?> createEditorPresenter() {
		return new EventEditorPresenter(parentSubject, this);
	}
	
	//private static Logger logger = LoggerFactory.getLogger(EventListPresenter.class);
	
}
