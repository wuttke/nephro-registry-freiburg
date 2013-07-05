package eu.wuttke.nrf.ui.subject;

import java.util.Collection;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.ui.page.AdminPage;
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
		SubjectListView lv = (SubjectListView)getListView();

		// suppress double click -> edit entity
		lv.addOpenAction(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				openButtonClicked();
			}
			private static final long serialVersionUID = 1L;
		});
		
		lv.addGoAdminAction(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				goToAdminClicked();						
			}
			private static final long serialVersionUID = 1L;
		});
	}
	
	public void openButtonClicked() {
		Subject s = getListView().getSelectedEntity();
		if (s != null) {
			getListView().getUI().getNavigator().navigateTo(SubjectDetailPage.PAGE_ID + "/" + s.getId().toString());
		}
	}
	
	protected void goToAdminClicked() {
		getListView().getUI().getNavigator().navigateTo(AdminPage.PAGE_ID);
	}
	
	@Override
	public Collection<Subject> loadEntities() {
		return Subject.findAllSubjects();
	}
	
	@Override
	public EditorPresenter<Subject, ?> createEditorPresenter() {
		return new SubjectEditorPresenter(this);
	}
	
}
