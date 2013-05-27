package eu.wuttke.nrf.ui.diagnosis;

import com.vaadin.event.Action.Listener;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import eu.wuttke.nrf.domain.diagnosis.Diagnosis;
import eu.wuttke.nrf.domain.diagnosis.Icd10Code;
import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.ui.component.OkCancelWindow;
import eu.wuttke.nrf.ui.presenter.EditorPresenter;
import eu.wuttke.nrf.ui.presenter.RefreshablePresenter;

public class DiagnosisEditorPresenter 
extends EditorPresenter<Diagnosis, DiagnosisEditorView> {
	
	private Subject parentSubject;
	
	public DiagnosisEditorPresenter(Subject parentSubject, RefreshablePresenter parentPresenter) {
		super(new DiagnosisEditorView(), parentPresenter);
		this.parentSubject = parentSubject;
		getEditorView().addChooseListener(new ClickListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				openIcd10CodeChooser();
			}
		});
	}
	
	protected void openIcd10CodeChooser() {
		Icd10CodeSearchPresenter p = new Icd10CodeSearchPresenter();
		p.showSearchWindow(getEditorView());
		p.setEntityChosenListener(new Listener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void handleAction(Object sender, Object target) {
				Icd10Code code = (Icd10Code)target;
				codeChosen(code);
			}
		});
	}

	protected void codeChosen(Icd10Code code) {
		Diagnosis d = getEditorView().retrieveEntity();
		if (d == null) d = new Diagnosis();
		d.setCode(code.getCode());
		d.setLabel(code.getName());
		getEditorView().displayEntity(d);
	}

	@Override
	public void showEditorWindow(OkCancelWindow w) {
		w.show(((DiagnosisListPresenter)getParent()).getListView().getUI(), "Edit Diagnosis", "600", "400");	
	}

	@Override
	public Diagnosis createEntity() {
		Diagnosis d = new Diagnosis();
		d.setSubject(parentSubject);
		return d;
	}
	
	@Override
	public void saveEntity(Diagnosis entity) {
		entity.merge();
	}

}
