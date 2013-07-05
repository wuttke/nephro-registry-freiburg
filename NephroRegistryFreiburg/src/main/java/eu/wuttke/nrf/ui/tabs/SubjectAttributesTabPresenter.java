package eu.wuttke.nrf.ui.tabs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Component;

import eu.wuttke.nrf.domain.attribute.AttributeBase;
import eu.wuttke.nrf.domain.attribute.AttributeCategory;
import eu.wuttke.nrf.domain.attribute.AttributeDao;
import eu.wuttke.nrf.domain.attribute.AttributeParentType;
import eu.wuttke.nrf.domain.attribute.AttributeType;
import eu.wuttke.nrf.domain.attribute.AttributeTypeUsage;
import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.domain.subject.SubjectAttribute;
import eu.wuttke.nrf.domain.subject.SubjectCategory;
import eu.wuttke.nrf.ui.attribute.AttributeOverviewView;
import eu.wuttke.nrf.ui.attribute.DynamicAttributeEditor;
import eu.wuttke.nrf.ui.presenter.RefreshablePresenter;

@Configurable
public class SubjectAttributesTabPresenter 
implements RefreshablePresenter {

	private AttributeOverviewView view = new AttributeOverviewView(AttributeParentType.SUBJECT);
	private Subject parentSubject;
	
	private List<AttributeCategory> availableCategories;
	private List<AttributeCategory> chosenCategories;
	private List<SubjectCategory> subjectCategories;
	
	private boolean blockValueChange = false;
	
	public SubjectAttributesTabPresenter() {
		view.addOptionGroupCategoriesValueChangeListener(new ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				if (!blockValueChange)
					saveChosenCategories();
			}
			private static final long serialVersionUID = 1L;
		});
	}
	
	protected void saveChosenCategories() {
		attributeDao.syncSubjectCategories(parentSubject, subjectCategories, view.getChosenCategories());
		refreshContent();
	}

	public void setParentSubject(Subject parentSubject) {
		this.parentSubject = parentSubject;
	}
	
	@Override
	public void refreshContent() {
		refreshAvailableCategories();
		refreshSubjectCategories();
		refreshAttributesContent();
	}
	
	private void refreshSubjectCategories() {
		subjectCategories = attributeDao.getSubjectCategoriesBySubject(parentSubject);
		chosenCategories = new ArrayList<AttributeCategory>();
		for (SubjectCategory category : subjectCategories) {
			AttributeCategory cat = category.getCategory();
			if (availableCategories.contains(cat))
				chosenCategories.add(cat);
			else
				logger.warn("found chosen category {} that is not available (anymore) for subject {}", cat.getId(), parentSubject.getId());
		}

		blockValueChange = true;
		view.setSelectedCategories(chosenCategories);
		blockValueChange = false;
	}

	private void refreshAvailableCategories() {
		availableCategories = attributeDao.getAttributeCategories(AttributeParentType.SUBJECT);

		blockValueChange = true;
		view.displayCategories(availableCategories);
		blockValueChange = false;
	}

	public void refreshAttributesContent() {
		logger.info("show subject attributes: {}", parentSubject.getLastName());
		List<SubjectAttribute> attributes = attributeDao.getSubjectAttributesBySubject(parentSubject);
		displayAttributes(attributes, AttributeTypeUsage.SUBJECT);
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
	
	private static Logger logger = LoggerFactory.getLogger(SubjectAttributesTabPresenter.class);

}
