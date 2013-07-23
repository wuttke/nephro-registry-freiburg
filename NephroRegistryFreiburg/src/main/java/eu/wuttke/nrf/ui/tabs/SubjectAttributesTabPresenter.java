package eu.wuttke.nrf.ui.tabs;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;

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
	private DynamicAttributeEditor attributeEditorView = null;
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
		
		view.addSaveListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				saveSubjectAttributes();
			}
			private static final long serialVersionUID = 1L;
		});
	}
	
	protected void saveSubjectAttributes() {
		List<SubjectAttribute> attributes = attributeDao.getSubjectAttributesBySubject(parentSubject);

		List<AttributeType> types = attributeDao.findAttributeTypesByUsageAndCategories(AttributeTypeUsage.SUBJECT, chosenCategories);
		for (AttributeType type : types) {
			if (!attributeDao.haveAttributeWithType(attributes, type)) {
				SubjectAttribute attribute = new SubjectAttribute();
				attribute.setAttributeType(type);
				attribute.setSubject(parentSubject);
				attributes.add(attribute);
			}
		}
		
		attributeEditorView.retrieveData(attributes);
		
		attributeDao.saveAttributes(attributes);
		logger.info("saved {} attributes", attributes.size());
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
		logger.info("displaying subject attributes for subject {}", parentSubject.getLastName());
		List<SubjectAttribute> attributes = attributeDao.getSubjectAttributesBySubject(parentSubject);
		
		List<AttributeType> types = attributeDao.findAttributeTypesByUsageAndCategories(AttributeTypeUsage.SUBJECT, chosenCategories);		
		view.setAttributesPanelTitle(MessageFormat.format("Subject Attributes: {0}", parentSubject.formatFullName()));
		
		attributeEditorView = new DynamicAttributeEditor();
		attributeEditorView.configureEditor(types);
		attributeEditorView.displayData(attributes);
		
		view.replaceAttributeEditor(attributeEditorView);
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
