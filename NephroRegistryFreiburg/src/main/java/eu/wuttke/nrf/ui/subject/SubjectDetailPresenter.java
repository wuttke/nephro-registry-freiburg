package eu.wuttke.nrf.ui.subject;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;
import com.vaadin.ui.TabSheet.Tab;

import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.ui.page.SubjectListPage;
import eu.wuttke.nrf.ui.presenter.RefreshablePresenter;
import eu.wuttke.nrf.ui.tabs.EncounterAttributesTabPresenter;
import eu.wuttke.nrf.ui.tabs.LabTabPresenter;
import eu.wuttke.nrf.ui.tabs.DiagnosisMedicationTabPresenter;
import eu.wuttke.nrf.ui.tabs.OverviewTabPresenter;
import eu.wuttke.nrf.ui.tabs.SubjectAttributesTabPresenter;

public class SubjectDetailPresenter 
implements RefreshablePresenter {

	private int subjectId;
	private SubjectDetailView view = new SubjectDetailView();
	private Subject subject;
	
	private List<RefreshablePresenter> presenters = new ArrayList<RefreshablePresenter>();
	private OverviewTabPresenter subjectOverviewTabPresenter = new OverviewTabPresenter();
	private LabTabPresenter labTabPresenter = new LabTabPresenter();
	private DiagnosisMedicationTabPresenter diagnosisMedicationTabPresenter = new DiagnosisMedicationTabPresenter();
	private SubjectAttributesTabPresenter subjectAttributesTabPresenter = new SubjectAttributesTabPresenter();
	private EncounterAttributesTabPresenter encounterAttributesTabPresenter = new EncounterAttributesTabPresenter();
	
	public SubjectDetailPresenter() {
		view.addBackButtonClickListener(new ClickListener() {			
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				getView().getUI().getNavigator().navigateTo(SubjectListPage.PAGE_ID);
			}
		});
		
		view.addNextButtonClickListener(new ClickListener() {			
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				goTo(1);
			}
		});

		view.addPrevButtonClickListener(new ClickListener() {			
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				goTo(-1);
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
		addTabPresenter("Diagnoses & Medication", diagnosisMedicationTabPresenter);
		addTabPresenter("Subject Attributes", subjectAttributesTabPresenter);
		addTabPresenter("Encounter Attributes", encounterAttributesTabPresenter);
	}
	
	protected void goTo(int direction) {
		String query = direction > 0 ? "SELECT id FROM Subject WHERE id > :id ORDER BY id ASC" : "SELECT id FROM Subject WHERE id < :id ORDER BY id DESC";
		TypedQuery<Long> q = Subject.entityManager().createQuery(query, Long.class);
		q.setMaxResults(1);
		q.setParameter("id", new Long(subjectId));
		List<Long> ids = q.getResultList();
		if (ids.size() == 0) {
			Notification.show("No more subjects");
			return;
		}
		
		Long id = ids.get(0);
		subjectId = id.intValue();
		refreshContent();
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
	
	protected void propagateSubject() {
		subjectOverviewTabPresenter.setParentSubject(subject);
		labTabPresenter.setParentSubject(subject);
		diagnosisMedicationTabPresenter.setParentSubject(subject);
		subjectAttributesTabPresenter.setParentSubject(subject);
		encounterAttributesTabPresenter.setParentSubject(subject);
	}
	
	@Override
	public void refreshContent() {
		logger.info("refreshContent: subjectId = {}", subjectId);
		subject = Subject.findSubject(new Long(subjectId));
		propagateSubject();
		refreshSelectedTab();

		if (StringUtils.isNotEmpty(subject.getBirthName()))
			view.setTitle(MessageFormat.format("{0}, geb. {2} ({1,date,short})", subject.formatFullName(), subject.getBirthdate(), subject.getBirthName()));
		else
			view.setTitle(MessageFormat.format("{0} ({1,date,short})", subject.formatFullName(), subject.getBirthdate()));	
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
