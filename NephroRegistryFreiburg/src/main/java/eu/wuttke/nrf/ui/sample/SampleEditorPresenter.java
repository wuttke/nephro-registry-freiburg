package eu.wuttke.nrf.ui.sample;

import com.vaadin.ui.UI;

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
		w.show(findUI(), "Edit Sample", "600", "500");	
	}
	
	@Override
	public UI findUI() {
		return ((SampleListPresenter)getParent()).getListView().getUI();
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
	
	@Override
	public void realDeleteEntity(Sample entity) {
		entity.remove();
	}
	
}
