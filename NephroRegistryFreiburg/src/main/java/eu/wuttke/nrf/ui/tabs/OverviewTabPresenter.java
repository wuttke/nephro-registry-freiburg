package eu.wuttke.nrf.ui.tabs;

import com.vaadin.ui.Component;

import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.ui.component.FourPanelsComposite;
import eu.wuttke.nrf.ui.encounter.EncounterListPresenter;
import eu.wuttke.nrf.ui.event.EventListPresenter;
import eu.wuttke.nrf.ui.presenter.RefreshablePresenter;
import eu.wuttke.nrf.ui.sample.SampleListPresenter;
import eu.wuttke.nrf.ui.subject.RelationListPresenter;

public class OverviewTabPresenter 
implements RefreshablePresenter {

	private Subject parentSubject;

	private SampleListPresenter sampleListPresenter = new SampleListPresenter();
	private EncounterListPresenter encounterListPresenter = new EncounterListPresenter();
	private RelationListPresenter relationListPresenter = new RelationListPresenter();
	private EventListPresenter eventListPresenter = new EventListPresenter();
	
	private Component view;
	
	public OverviewTabPresenter() {
		view = new FourPanelsComposite(
				new String[] {"Encounters", "Family", "Samples", "Events"},
				new Component[] {encounterListPresenter.getView(), relationListPresenter.getView(), 
						sampleListPresenter.getView(), eventListPresenter.getView()}
				);
	}

	public void setParentSubject(Subject parentSubject) {
		this.parentSubject = parentSubject;
	}
	
	@Override
	public void refreshContent() {
		encounterListPresenter.setParentSubject(parentSubject);
		relationListPresenter.setParentSubject(parentSubject);
		sampleListPresenter.setParentSubject(parentSubject);
		eventListPresenter.setParentSubject(parentSubject);

		encounterListPresenter.refreshContent();
		relationListPresenter.refreshContent();
		sampleListPresenter.refreshContent();
		eventListPresenter.refreshContent();
	}
	
	@Override
	public Component getView() {
		return view;
	}
	
}
