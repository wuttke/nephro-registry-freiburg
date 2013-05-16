package eu.wuttke.nrf.ui.subject;

import java.util.Collection;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.ui.page.SubjectDetailPage;
import eu.wuttke.nrf.ui.presenter.EditorPresenter;
import eu.wuttke.nrf.ui.presenter.ListPresenter;


public class SubjectListPresenter 
extends ListPresenter<Subject, SubjectListView> {
	
	public SubjectListPresenter() {
		super(new SubjectListView());
	}
	
	@Override
	protected void addDoubleClickHandler() {
		// suppress double click -> edit entity
		((SubjectListView)getListView()).addOpenAction(new ClickListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				openButtonClicked();
			}
		});
	}
	
	public void openButtonClicked() {
		Subject s = getListView().getSelectedEntity();
		if (s != null) {
			getListView().getUI().getNavigator().navigateTo(SubjectDetailPage.PAGE_ID + "/" + s.getId().toString());
		}
	}
	
	@Override
	public Collection<? extends Subject> loadEntities() {
		return Subject.findAllSubjects();
	}
	
	@Override
	public EditorPresenter<Subject, ?> createEditorPresenter() {
		return new SubjectEditorPresenter(this);
	}
	
}
