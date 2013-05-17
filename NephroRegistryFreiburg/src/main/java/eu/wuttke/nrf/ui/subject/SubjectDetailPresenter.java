package eu.wuttke.nrf.ui.subject;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;
import com.vaadin.ui.TabSheet.Tab;

import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.ui.lab.LabTabPresenter;
import eu.wuttke.nrf.ui.page.SubjectListPage;
import eu.wuttke.nrf.ui.presenter.RefreshablePresenter;

public class SubjectDetailPresenter 
implements RefreshablePresenter {

	private int subjectId;
	private SubjectDetailView view = new SubjectDetailView();
	private Subject subject;
	
	private List<RefreshablePresenter> presenters = new ArrayList<RefreshablePresenter>();
	private SubjectOverviewTabPresenter subjectOverviewTabPresenter = new SubjectOverviewTabPresenter();
	private LabTabPresenter labTabPresenter = new LabTabPresenter();
	private SubjectAttributesTabPresenter subjectAttributesTabPresenter = new SubjectAttributesTabPresenter();
	
	public SubjectDetailPresenter() {
		view.addBackButtonClickListener(new ClickListener() {			
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				getView().getUI().getNavigator().navigateTo(SubjectListPage.PAGE_ID);
			}
		});
		
		view.getTabSheet().addSelectedTabChangeListener(new SelectedTabChangeListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void selectedTabChange(SelectedTabChangeEvent event) {
				// block first calls to refreshContent during TabSheet construction
				// (subject is set after construction)
				if (subjectId > 0)
					refreshContent();
			}
		});

		addTabPresenter("Overview", subjectOverviewTabPresenter);
		addTabPresenter("Lab Values", labTabPresenter);
		// Results
		addTabPresenter("Attributes", subjectAttributesTabPresenter);
	}
	
	private void addTabPresenter(String title, RefreshablePresenter presenter) {
		presenters.add(presenter);
		getView().getTabSheet().addTab(presenter.getView(), title);
	}

	@Override
	public SubjectDetailView getView() {
		return view;
	}
	
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	
	@Override
	public void refreshContent() {
		logger.info("refreshContent: subjectId = {}", subjectId);
		subject = Subject.findSubject(new Long(subjectId));
		view.setTitle(subject.formatFullName());
		subjectOverviewTabPresenter.setParentSubject(subject);
		labTabPresenter.setParentSubject(subject);
		subjectAttributesTabPresenter.setParentSubject(subject);
		refreshSelectedTab();
	}

	private void refreshSelectedTab() {
		Component tabC = getView().getTabSheet().getSelectedTab();
		if (tabC != null) {
			Tab tab = getView().getTabSheet().getTab(tabC);
			int index =  getView().getTabSheet().getTabPosition(tab);
			presenters.get(index).refreshContent();
		}
	}
	
	private static Logger logger = LoggerFactory.getLogger(SubjectDetailPresenter.class);
	
}
