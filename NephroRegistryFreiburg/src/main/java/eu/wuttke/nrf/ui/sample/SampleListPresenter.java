package eu.wuttke.nrf.ui.sample;

import java.util.Collection;

import eu.wuttke.nrf.domain.sample.Sample;
import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.ui.presenter.EditorPresenter;
import eu.wuttke.nrf.ui.presenter.ListPresenter;

public class SampleListPresenter 
extends ListPresenter<Sample, SampleListView> {
	
	private Subject subject;
	
	public SampleListPresenter() {
		super(new SampleListView());
	}

	public void setParentSubject(Subject parentSubject) {
		this.subject = parentSubject;
	}
	
	@Override
	public Collection<Sample> loadEntities() {
		return Sample.findSamplesBySubject(subject).getResultList();
	}
	
	@Override
	public EditorPresenter<Sample, ?> createEditorPresenter() {
		return new SampleEditorPresenter(subject, this);
	}

}
