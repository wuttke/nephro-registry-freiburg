package eu.wuttke.nrf.ui.subject;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.javabean.RooJavaBean;

import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.domain.subject.SubjectRepository;
import eu.wuttke.nrf.ui.page.AdminPage;
import eu.wuttke.nrf.ui.page.SubjectDetailPage;
import eu.wuttke.nrf.ui.presenter.EditorPresenter;
import eu.wuttke.nrf.ui.presenter.ListPresenter;

@Configurable
@RooJavaBean
public class SubjectListPresenter 
extends ListPresenter<Subject, SubjectListView> {
	
	public SubjectListPresenter() {
		super(new SubjectListView());

		getListView().provideSearchSupport(true, new ClickListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				refreshContent();
			}
		}, new BlurListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void blur(BlurEvent event) {
				refreshContent();
			}
		});
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
			getListView().getUI().getNavigator().navigateTo(SubjectDetailPage.PAGE_ID + "/" + s.getId().toString()); //$NON-NLS-1$
		}
	}
	
	protected void goToAdminClicked() {
		getListView().getUI().getNavigator().navigateTo(AdminPage.PAGE_ID);
	}
	
	@Override
	public Collection<Subject> loadEntities() {
		String query = getListView().getSearchQuery();
		if (StringUtils.isBlank(query))
			return Subject.findAllSubjects();
		else 
			return subjectRepository.performSubjectSearch(query);
	}
	
	@Override
	public EditorPresenter<Subject, ?> createEditorPresenter() {
		return new SubjectEditorPresenter(this);
	}
	
	@Autowired
	private SubjectRepository subjectRepository;
	
}
