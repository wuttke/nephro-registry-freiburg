package eu.wuttke.nrf.ui.encounter;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.vaadin.ui.Notification;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import eu.wuttke.nrf.domain.encounter.Encounter;
import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.interf.kis.KisVisitImporter;
import eu.wuttke.nrf.ui.presenter.EditorPresenter;
import eu.wuttke.nrf.ui.presenter.ListPresenter;

@Configurable
public class EncounterListPresenter 
extends ListPresenter<Encounter, EncounterListView> {
	
	private Subject parentSubject;
	
	public EncounterListPresenter() {
		super(new EncounterListView());
	}
	
	@Override
	protected void addViewActions() {
		super.addViewActions();
		addSyncAction();
	}
	
	private void addSyncAction() {
		getListView().addSyncAction(new ClickListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				doSyncVisits();
			}
		});
	}
	
	protected void doSyncVisits() {
		List<Encounter> visits = kisVisitImporter.mergeKisVisits(parentSubject); 
		logger.info("retrieved {} KIS visits", visits.size());
		refreshContent();
		Notification.show(String.format("Retrieved %d new visits", visits.size()), Notification.Type.TRAY_NOTIFICATION);
	}

	public void setParentSubject(Subject parentSubject) {
		this.parentSubject = parentSubject;
	}
	
	@Override
	public Collection<? extends Encounter> loadEntities() {
		return Encounter.findEncountersBySubject(parentSubject).getResultList();
	}
	
	@Override
	public EditorPresenter<Encounter, ?> createEditorPresenter() {
		return new EncounterEditorPresenter(parentSubject, this);
	}
	
	public void setKisVisitImporter(KisVisitImporter kisVisitImporter) {
		this.kisVisitImporter = kisVisitImporter;
	}
	
	@Autowired
	private KisVisitImporter kisVisitImporter;
	
	private static Logger logger = LoggerFactory.getLogger(EncounterListPresenter.class);
	
}
