package eu.wuttke.nrf.ui.tabs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Component;

import eu.wuttke.nrf.domain.misc.PrecisionDateUtil;
import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.domain.visit.Visit;
import eu.wuttke.nrf.ui.attribute.AttributeOverviewView;
import eu.wuttke.nrf.ui.presenter.RefreshablePresenter;
import eu.wuttke.nrf.ui.visit.VisitListPresenter;

public class AttributesTabPresenter 
implements RefreshablePresenter {

	private AttributeOverviewView view = new AttributeOverviewView();;
	private VisitListPresenter visitListPresenter = new VisitListPresenter();
	private Subject parentSubject;
	
	private boolean blockValueChange = false;
	
	public AttributesTabPresenter() {
		visitListPresenter.setListView(view.getVisitListView());
		view.addOptionGroupValueChangeListener(new ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				if (!blockValueChange)
					refreshAttributesContent();
			}
			private static final long serialVersionUID = 1L;
		});
		view.getVisitListView().addTableItemClickListener(new ItemClickListener() {
			@Override
			@SuppressWarnings("unchecked")
			public void itemClick(ItemClickEvent event) {
				blockValueChange = true;
				view.setShowSubjectAttribute(false);
				blockValueChange = false;
				
				BeanItem<Visit> item = (BeanItem<Visit>)event.getItem();
				refreshVisitAttributesContent(item.getBean());
			}
			private static final long serialVersionUID = 1L;
		});
	}
	
	public void setParentSubject(Subject parentSubject) {
		this.parentSubject = parentSubject;
	}
	
	@Override
	public void refreshContent() {
		visitListPresenter.setParentSubject(parentSubject);
		visitListPresenter.refreshContent();
		refreshAttributesContent();
	}
	
	public void refreshAttributesContent() {
		boolean showSubjectAttributes = view.isShowSubjectAttributes();
		if (showSubjectAttributes) {
			refreshSubjectAttributesContent();
		} else {
			Visit visit = view.getVisitListView().getSelectedEntity();
			if (visit != null)
				refreshVisitAttributesContent(visit);
		}
	}
	
	public void refreshSubjectAttributesContent() {
		logger.info("show subject attributes: {}", parentSubject.getLastName());
		view.setAttributesPanelTitle("Subject Attributes");
	}

	public void refreshVisitAttributesContent(Visit visit) {
		logger.info("show visit attributes: {}", visit.getLabel());
		view.setAttributesPanelTitle("Visit Attributes: " + PrecisionDateUtil.formatDate(visit.getVisitDateTime(), null));
	}

	@Override
	public Component getView() {
		return view;
	}
	
	private static Logger logger = LoggerFactory.getLogger(AttributesTabPresenter.class);

}
