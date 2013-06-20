package eu.wuttke.nrf.ui.encounter;

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
		w.show(((EncounterListPresenter)getParent()).getListView().getUI(), "Edit Encounter", "400", "300");	
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

}
