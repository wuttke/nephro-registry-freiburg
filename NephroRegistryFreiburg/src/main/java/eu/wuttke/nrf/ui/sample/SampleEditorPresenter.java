package eu.wuttke.nrf.ui.sample;

import eu.wuttke.nrf.domain.sample.Sample;
import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.ui.component.OkCancelWindow;
import eu.wuttke.nrf.ui.presenter.EditorPresenter;
import eu.wuttke.nrf.ui.presenter.RefreshablePresenter;

public class SampleEditorPresenter 
extends EditorPresenter<Sample, SampleEditorView> {
	
	private Subject subject;
	
	public SampleEditorPresenter(Subject subject, RefreshablePresenter parent) {
		super(new SampleEditorView(), parent);
		this.subject = subject;
	}
	
	@Override
	public void showEditorWindow(OkCancelWindow w) {
		w.show(((SampleListPresenter)getParent()).getListView().getUI(), "Edit Sample", "600", "500");	
	}

	@Override
	public Sample createEntity() {
		Sample s = new Sample();
		s.setSubject(subject);
		return s;
	}
	
	@Override
	public void saveEntity(Sample entity) {
		entity.merge();
	}

	
}
