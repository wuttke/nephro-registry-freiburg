package eu.wuttke.nrf.ui.medication;

import org.apache.commons.lang3.StringUtils;

import com.vaadin.event.Action.Listener;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import eu.wuttke.nrf.domain.medication.AtcCode;
import eu.wuttke.nrf.domain.medication.Medicament;
import eu.wuttke.nrf.domain.medication.Medication;
import eu.wuttke.nrf.domain.medication.PharmaceuticalForm;
import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.ui.component.OkCancelWindow;
import eu.wuttke.nrf.ui.presenter.EditorPresenter;
import eu.wuttke.nrf.ui.presenter.RefreshablePresenter;

public class MedicationEditorPresenter 
extends EditorPresenter<Medication, MedicationEditorView> {
	
	private Subject parentSubject;
	
	public MedicationEditorPresenter(Subject parentSubject, RefreshablePresenter parentPresenter) {
		super(new MedicationEditorView(), parentPresenter);
		this.parentSubject = parentSubject;
		getEditorView().addChooseListener(new ClickListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				openMedicationChooser();
			}
		});
	}
	
	@Override
	protected void displayEntity() {
		super.displayEntity();
		
		if (StringUtils.isNotEmpty(getEntity().getAtcCode())) {
			AtcCode a = AtcCode.findAtcCode(getEntity().getAtcCode());
			getEditorView().displayAtcCode(String.format("%s (%s)", a.getCode(), a.getName()));
		} else
			getEditorView().displayAtcCode("---");
		
		if (getEntity().getMid() > 0) {
			Medicament m = Medicament.findMedicament(getEntity().getMid());
			getEditorView().displayMedicament(String.format("%s (%d)", m.getTradename(), m.getMid()));
		} else
			getEditorView().displayMedicament(getEntity().getLabel());
		
		if (StringUtils.isNotEmpty(getEntity().getPharmaceuticalForm())) {
			PharmaceuticalForm pf = PharmaceuticalForm.findPharmaceuticalForm(getEntity().getPharmaceuticalForm());
			getEditorView().displayPharmaceuticalForm(String.format("%s (%s)", pf.getShortcut(), pf.getName()));
		} else
			getEditorView().displayPharmaceuticalForm("---");
	}
	
	protected void openMedicationChooser() {
		MedicamentSearchPresenter p = new MedicamentSearchPresenter();
		p.showSearchWindow(getEditorView());
		p.setEntityChosenListener(new Listener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void handleAction(Object sender, Object target) {
				Medicament m = (Medicament)target;
				codeChosen(m);
			}
		});
	}

	protected void codeChosen(Medicament m) {
		Medication med = getEntity();
		med.setAtcCode(m.getAtcCode());
		med.setLabel(m.getTradename());
		med.setMid(m.getMid());
		med.setPharmaceuticalForm(m.getPharmaceuticalForm());
		displayEntity();
	}
	
	@Override
	public void newEntity() {
		super.newEntity();
		openMedicationChooser();
	}

	@Override
	public void showEditorWindow(OkCancelWindow w) {
		w.show(findUI(), "Edit Medication", "600", "500");	
	}
	
	@Override
	public UI findUI() {
		return ((MedicationListPresenter)getParent()).getListView().getUI();
	}

	@Override
	public Medication createEntity() {
		Medication m = new Medication();
		m.setSubject(parentSubject);
		return m;
	}
	
	@Override
	public void saveEntity(Medication entity) {
		entity.merge();
	}
	
	@Override
	public void realDeleteEntity(Medication entity) {
		entity.remove();
	}

}
