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
import eu.wuttke.nrf.domain.attribute.AttributeParentType;
import eu.wuttke.nrf.domain.attribute.AttributeType;
import eu.wuttke.nrf.domain.attribute.AttributeTypeUsage;
import eu.wuttke.nrf.domain.encounter.Encounter;
import eu.wuttke.nrf.domain.encounter.EncounterAttribute;
import eu.wuttke.nrf.domain.misc.PrecisionDateUtil;
import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.domain.subject.SubjectAttribute;
import eu.wuttke.nrf.ui.attribute.AttributeOverviewView;
import eu.wuttke.nrf.ui.attribute.DynamicAttributeEditor;
import eu.wuttke.nrf.ui.encounter.EncounterListPresenter;
import eu.wuttke.nrf.ui.presenter.RefreshablePresenter;

@Configurable
public class EncounterAttributesTabPresenter 
implements RefreshablePresenter {

	private AttributeOverviewView view = new AttributeOverviewView(AttributeParentType.ENCOUNTER);
	private EncounterListPresenter encounterListPresenter = new EncounterListPresenter();
	private Subject parentSubject;
	private List<AttributeCategory> categories;
	
	private boolean blockValueChange = false;
	
	public EncounterAttributesTabPresenter() {
		encounterListPresenter.setListView(view.getEncounterListView());
		/*view.addOptionGroupParentValueChangeListener(new ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				if (!blockValueChange)
					refreshAttributesContent();
			}
			private static final long serialVersionUID = 1L;
		});*/
		view.getEncounterListView().addTableItemClickListener(new ItemClickListener() {
			@Override
			@SuppressWarnings("unchecked")
			public void itemClick(ItemClickEvent event) {
				blockValueChange = true;
				//view.setShowSubjectAttribute(false);
				blockValueChange = false;
				
				BeanItem<Encounter> item = (BeanItem<Encounter>)event.getItem();
				refreshEncounterAttributesContent(item.getBean());
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
		encounterListPresenter.setParentSubject(parentSubject);
		encounterListPresenter.refreshContent();
		refreshCategoriesContent();
		refreshAttributesContent();
	}
	
	private void refreshCategoriesContent() {
		blockValueChange = true;
		categories = attributeDao.getAttributeCategories(AttributeParentType.ENCOUNTER);
		view.displayCategories(categories);
		blockValueChange = false;
	}

	public void refreshAttributesContent() {
		boolean showSubjectAttributes = false;//AttributeParentType parentTypeview.isShowSubjectAttributes();
		if (showSubjectAttributes) {
			refreshSubjectAttributesContent();
		} else {
			Encounter encounter = view.getEncounterListView().getSelectedEntity();
			if (encounter != null)
				refreshEncounterAttributesContent(encounter);
		}
	}
	
	public void refreshSubjectAttributesContent() {
		logger.info("show subject attributes: {}", parentSubject.getLastName());
		view.setAttributesPanelTitle("Subject Attributes");
		List<SubjectAttribute> attributes = attributeDao.getSubjectAttributesBySubject(parentSubject);
		displayAttributes(attributes, AttributeTypeUsage.SUBJECT);
	}

	public void refreshEncounterAttributesContent(Encounter encounter) {
		logger.info("show encounter attributes: {}", encounter.getLabel());
		view.setAttributesPanelTitle("Encounter Attributes: " + PrecisionDateUtil.formatDate(encounter.getEncounterDate(), null));
		List<EncounterAttribute> attributes = attributeDao.getEncounterAttributesByEncounter(encounter);
		displayAttributes(attributes, AttributeTypeUsage.ENCOUNTER);
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
	
	private static Logger logger = LoggerFactory.getLogger(EncounterAttributesTabPresenter.class);

}
