package eu.wuttke.nrf.ui.tabs;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Component;

import eu.wuttke.nrf.domain.attribute.AttributeBase;
import eu.wuttke.nrf.domain.attribute.AttributeCategory;
import eu.wuttke.nrf.domain.attribute.AttributeDao;
import eu.wuttke.nrf.domain.attribute.AttributeType;
import eu.wuttke.nrf.domain.attribute.AttributeTypeUsage;
import eu.wuttke.nrf.domain.misc.PrecisionDateUtil;
import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.domain.subject.SubjectAttribute;
import eu.wuttke.nrf.domain.visit.Visit;
import eu.wuttke.nrf.domain.visit.VisitAttribute;
import eu.wuttke.nrf.ui.attribute.AttributeOverviewView;
import eu.wuttke.nrf.ui.attribute.DynamicAttributeEditor;
import eu.wuttke.nrf.ui.presenter.RefreshablePresenter;
import eu.wuttke.nrf.ui.visit.VisitListPresenter;

@Configurable
public class AttributesTabPresenter 
implements RefreshablePresenter {

	private AttributeOverviewView view = new AttributeOverviewView();;
	private VisitListPresenter visitListPresenter = new VisitListPresenter();
	private Subject parentSubject;
	private List<AttributeCategory> categories;
	
	private boolean blockValueChange = false;
	
	public AttributesTabPresenter() {
		visitListPresenter.setListView(view.getVisitListView());
		view.addOptionGroupParentValueChangeListener(new ValueChangeListener() {
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
		view.addOptionGroupCategoriesValueChangeListener(new ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				if (!blockValueChange)
					refreshAttributesContent();
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
		refreshCategoriesContent();
		refreshAttributesContent();
	}
	
	private void refreshCategoriesContent() {
		blockValueChange = true;
		categories = attributeDao.getAttributeCategories();
		view.displayCategories(categories);
		blockValueChange = false;
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
		List<SubjectAttribute> attributes = attributeDao.getSubjectAttributesBySubject(parentSubject);
		displayAttributes(attributes, AttributeTypeUsage.SUBJECT);
	}

	public void refreshVisitAttributesContent(Visit visit) {
		logger.info("show visit attributes: {}", visit.getLabel());
		view.setAttributesPanelTitle("Visit Attributes: " + PrecisionDateUtil.formatDate(visit.getVisitDateTime(), null));
		List<VisitAttribute> attributes = attributeDao.getVisitAttributesByVisit(visit);
		displayAttributes(attributes, AttributeTypeUsage.VISIT);
	}
	
	protected void displayAttributes(List<? extends AttributeBase> attributes, AttributeTypeUsage usage) {
		List<AttributeType> types = getAttributeTypesToDisplay(usage);
		
		DynamicAttributeEditor dae = new DynamicAttributeEditor();
		dae.configureEditor(types);
		dae.displayData(attributes);
		
		view.replaceAttributeEditor(dae);
	}

	protected List<AttributeType> getAttributeTypesToDisplay(AttributeTypeUsage usage) {
		Collection<AttributeCategory> categories = view.getChosenCategories();
		List<AttributeType> types = attributeDao.findAttributeTypesByUsageAndCategories(usage, categories);
		return types;
	}

	@Override
	public Component getView() {
		return view;
	}
	
	public void setAttributeDao(AttributeDao attributeDao) {
		this.attributeDao = attributeDao;
	}
	
	@Autowired
	private AttributeDao attributeDao;
	
	private static Logger logger = LoggerFactory.getLogger(AttributesTabPresenter.class);

}
