package eu.wuttke.nrf.controller.subject;

import java.util.Collection;
import java.util.List;

import eu.wuttke.nrf.controller.SimpleListController;
import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.ui.component.OkCancelWindow;
import eu.wuttke.nrf.ui.subject.SubjectEditorView;
import eu.wuttke.nrf.ui.subject.SubjectListView;


public class SubjectListController 
implements SimpleListController<Subject> {

	private SubjectListView view = new SubjectListView();
	private List<Subject> subjects;
	
	@Override
	public Collection<? extends Subject> loadEntities() {
		subjects = Subject.findAllSubjects();
		return subjects;
	}
	
	@Override
	public Class<? super Subject> getEntityClass() {
		return Subject.class;
	}
	
	@Override
	public void refreshContent() {
		view.displayEntities(loadEntities());
	}
	
	public SubjectListView getView() {
		return view;
	}
	
	protected void newEntity() {
		Subject s = new Subject();
		OkCancelWindow w = new OkCancelWindow(new SubjectEditorView(s));
		w.show(view.getUI(), "New Patient", "600", "400");
		// onOk: s.persist()
	}
	
	protected void changeEntity(Subject s) {
		OkCancelWindow w = new OkCancelWindow(new SubjectEditorView(s));
		w.show(view.getUI(), "Edit Patient", "600", "400");		
	}

	protected void deleteEntity(Subject s) {
	}

	
}
