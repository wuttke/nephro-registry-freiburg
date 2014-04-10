package eu.wuttke.nrf.ui.admin;

import org.apache.commons.lang3.ArrayUtils;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;

import eu.wuttke.nrf.domain.attribute.AttributeCategory;
import eu.wuttke.nrf.domain.attribute.AttributeType;
import eu.wuttke.nrf.domain.diagnosis.DiagnosisCode;
import eu.wuttke.nrf.domain.event.EventType;
import eu.wuttke.nrf.domain.user.UserInfo;
import eu.wuttke.nrf.ui.admin.AdminBeanField.AdminBeanFieldType;
import eu.wuttke.nrf.ui.presenter.RefreshablePresenter;


public class AdminOverviewPresenter 
implements RefreshablePresenter {

	private AdminOverviewView view = new AdminOverviewView();
	private String[] labels;
	private int currentListPresenter = -1;
	
	private AdminBeanField[] attributeCategoryFields = new AdminBeanField[] {
			new AdminBeanField("sequenceNumber", "Sequence Number", AdminBeanFieldType.PLAIN_TEXT, true, "200px"),
			new AdminBeanField("label", "Label", AdminBeanFieldType.PLAIN_TEXT, true, "400px"),
			new AdminBeanField("parentType", "Parent Type", null, true, "300px"),
	};

	private AdminBeanField[] userInfoFields = new AdminBeanField[] {
			new AdminBeanField("userName", "User Name", AdminBeanFieldType.PLAIN_TEXT, true, "200px"),
			new AdminBeanField("password", "Password", AdminBeanFieldType.PLAIN_TEXT, true, "400px"),
			new AdminBeanField("lastName", "Last Name", AdminBeanFieldType.PLAIN_TEXT, true, "400px"),
			new AdminBeanField("firstName", "First Name", AdminBeanFieldType.PLAIN_TEXT, true, "400px"),
			new AdminBeanField("title", "Title", AdminBeanFieldType.PLAIN_TEXT, true, "200px"),
			new AdminBeanField("email", "E-Mail", AdminBeanFieldType.PLAIN_TEXT, true, "400px")
	};
	
	private AdminBeanField[] attributeTypeFields = new AdminBeanField[] {
			new AdminBeanField("sequenceNumber", "Sequence Number", AdminBeanFieldType.PLAIN_TEXT, true, "200px"), 
			new AdminBeanField("shortcut", "Shortcut", AdminBeanFieldType.PLAIN_TEXT, true, "300px"),
			new AdminBeanField("label", "Label", AdminBeanFieldType.PLAIN_TEXT, true, "400px"),
			new AdminBeanField("groupName", "Group Name", AdminBeanFieldType.PLAIN_TEXT, true, "400px"),
			new AdminBeanField("dataType", "Data Type", null, true, "300px"),
			new AdminBeanField("minimumLength", "Minimum Length", AdminBeanFieldType.PLAIN_TEXT, false, "200px"),
			new AdminBeanField("maximumLength", "Maximum Length", AdminBeanFieldType.PLAIN_TEXT, false, "200px"),
			new AdminBeanField("minimumValue", "Minimum Value", AdminBeanFieldType.PLAIN_TEXT, false, "200px"),
			new AdminBeanField("maximumValue", "Maximum Value", AdminBeanFieldType.PLAIN_TEXT, false, "200px"),
			new AdminBeanField("defaultValue", "Default Value", AdminBeanFieldType.PLAIN_TEXT, false, "400px"),
			new AdminBeanField("enumItems", "Enumeration Items", AdminBeanFieldType.PLAIN_TEXT, false, "400px"),
			new AdminBeanField("required", "Required", null, true, "200px"),
			new AdminBeanField("attributeUsage", "Attribute Usage", null, true, "300px"),
			new AdminBeanField("category", "Attribute Category", null, true, "400px")
	};
	
	private AdminBeanField[] eventTypeFields = new AdminBeanField[] {
			new AdminBeanField("code", "Code", AdminBeanFieldType.PLAIN_TEXT, true, "200px"),
			new AdminBeanField("label", "Label", AdminBeanFieldType.PLAIN_TEXT, true, "400px"),
			new AdminBeanField("description", "Description", AdminBeanFieldType.MULTILINE_TEXT, false, "400px"),
	};

	private AdminBeanField[] diagnosisCodeFields = new AdminBeanField[] {
			new AdminBeanField("code", "Code", AdminBeanFieldType.PLAIN_TEXT, true, "200px"),
			new AdminBeanField("label", "Label", AdminBeanFieldType.PLAIN_TEXT, true, "400px"),
			new AdminBeanField("codingSystem", "Coding System", null, true, "400px"),
			new AdminBeanField("description", "Description", AdminBeanFieldType.MULTILINE_TEXT, false, "400px"),
	};
	
	private AdminBeanListPresenter[] presenters = new AdminBeanListPresenter[] {
			new AdminBeanListPresenter(AttributeCategory.class, "Attribute Categories",
					new String[]{"sequenceNumber", "label", "parentType"},
					new String[]{"Sequence Number", "Label", "Parent Type"}, 
					new float[]{1f,4f,2f},
					attributeCategoryFields, this),

			new AdminBeanListPresenter(AttributeType.class, "Attribute Types", 
					new String[]{"sequenceNumber", "shortcut", "label", "dataType"},
					new String[]{"Sequence Number", "Shortcut", "Label", "Data Type"}, 
					new float[]{1f,2f,3f,2f},
					attributeTypeFields, this),

			new AdminBeanListPresenter(EventType.class, "Event Types", 
					new String[]{"code", "label", "description"},
					new String[]{"Code", "Label", "Description"}, 
					new float[]{1f,3f,2f},
					eventTypeFields, this),

			new AdminBeanListPresenter(DiagnosisCode.class, "Diagnosis Codes", 
					new String[]{"code", "codingSystem", "label", "description"},
					new String[]{"Code", "Coding System", "Label", "Description"}, 
					new float[]{1f,2f,3f,2f},
					diagnosisCodeFields, this),

			new AdminBeanListPresenter(UserInfo.class, "Users", 
					new String[]{"userName", "lastName", "firstName"},
					new String[]{"User Name", "Last Name", "First Name"}, 
					new float[]{1f,2f,2f},
					userInfoFields, this)
	};
	
	public AdminOverviewPresenter() {
		view.addChosenAdminAreaChangedListener(new ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				chooseAdminArea((String)event.getProperty().getValue());
			}
			private static final long serialVersionUID = 1L;
		});
		
		labels = new String[presenters.length];
		for (int i = 0; i < labels.length; i++)
			labels[i] = presenters[i].getTitle();
	}
	
	protected void chooseAdminArea(String areaLabel) {
		int idx = ArrayUtils.indexOf(labels, areaLabel);
		if (idx == -1)
			view.displayListView(null);
		else {
			AdminBeanListPresenter presenter = presenters[idx];
			view.displayListView(presenter.getView());
			presenter.refreshContent();
		}
		currentListPresenter = idx;
	}

	public AdminOverviewView getView() {
		return view;
	}

	@Override
	public void refreshContent() {
		view.displayAdminAreas(labels);
		if (currentListPresenter != -1)
			presenters[currentListPresenter].refreshContent();
	}
	
}
