package eu.wuttke.nrf.ui.encounter;

import com.vaadin.ui.UI;

import eu.wuttke.nrf.domain.encounter.Encounter;
import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.ui.component.OkCancelWindow;
import eu.wuttke.nrf.ui.presenter.EditorPresenter;
import eu.wuttke.nrf.ui.presenter.RefreshablePresenter;

public class EncounterEditorPresenter 
extends EditorPresenter<Encounter, EncounterEditorView> {
	
	private Subject parentSubject;
	
	public EncounterEditorPresenter(Subject parentSubject, RefreshablePresenter parentPresenter) {
		super(new EncounterEditorView(), parentPresenter);
		this.parentSubject = parentSubject;
	}
	
	@Override
	public void showEditorWindow(OkCancelWindow w) {
		w.show(findUI(), "Edit Encounter", "500", "400");	
	}
	
	@Override
	public UI findUI() {
		return ((EncounterListPresenter)getParent()).getListView().getUI();
	}

	@Override
	public Encounter createEntity() {
		Encounter d = new Encounter();
		d.setSubject(parentSubject);
		return d;
	}
	
	@Override
	public void saveEntity(Encounter entity) {
		entity.merge();
	}
	
	@Override
	public void realDeleteEntity(Encounter entity) {
		entity.remove();
	}

}
