package eu.wuttke.nrf.ui.subject;

import com.vaadin.event.Action.Listener;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;

import eu.wuttke.nrf.domain.subject.Gender;
import eu.wuttke.nrf.domain.subject.Relation;
import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.ui.component.OkCancelWindow;
import eu.wuttke.nrf.ui.presenter.EditorPresenter;
import eu.wuttke.nrf.ui.presenter.RefreshablePresenter;

public class RelationEditorPresenter 
extends EditorPresenter<Relation, RelationEditorView> {
	
	private Subject parentSubject;
	
	public RelationEditorPresenter(Subject parentSubject, RefreshablePresenter parentPresenter) {
		super(new RelationEditorView(), parentPresenter);
		this.parentSubject = parentSubject;
		
		getEditorView().addChooseClickListeners(new ClickListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				openSubjectChooser(Gender.FEMALE);
			}
		}, new ClickListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				openSubjectChooser(Gender.MALE);
			}
		});
	}
	
	@Override
	protected boolean validateEntity(Relation entity) {
		if (entity.getFather() == null && entity.getMother() == null) {
			Notification.show("Data missing", "Please specify at least either the father or the mother.", Notification.Type.ERROR_MESSAGE);
			return false;
		}

		return super.validateEntity(entity);
	}
		
	protected void openSubjectChooser(final Gender gender) {
		SubjectSearchPresenter p = new SubjectSearchPresenter();
		p.showSearchWindow(getEditorView());
		p.setEntityChosenListener(new Listener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void handleAction(Object sender, Object target) {
				Subject code = (Subject)target;
				subjectChosen(gender, code);
			}
		});
	}

	protected void subjectChosen(Gender gender, Subject subject) {
		if (subject.getGender() != gender) {
			Notification.show("Gender mismatch", String.format("Expected: %s, chosen: %s", subject.getGender().toString(), gender.toString()), Notification.Type.ERROR_MESSAGE);
		} else {
			Relation d = getEntity();
			if (gender == Gender.FEMALE)
				d.setMother(subject);
			else if (gender == Gender.MALE)
				d.setFather(subject);
			getEditorView().displayEntity(d);
		}
	}

	@Override
	public void showEditorWindow(OkCancelWindow w) {
		w.show(((RelationListPresenter)getParent()).getListView().getUI(), "Edit Relation", "600", "400");	
	}

	@Override
	public Relation createEntity() {
		Relation s = new Relation();
		s.setSubject(parentSubject);
		return s;
	}
	
	@Override
	public void saveEntity(Relation entity) {
		entity.merge();
	}
	
	@Override
	public void realDeleteEntity(Relation entity) {
		entity.remove();
	}

}
