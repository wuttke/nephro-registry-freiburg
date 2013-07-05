package eu.wuttke.nrf.ui.admin;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;

import eu.wuttke.nrf.ui.view.EditorView;

public class AdminBeanEditorView 
extends CustomComponent
implements EditorView<Object> {
	
	protected Object bean;
	protected BeanItem<Object> item;
	protected AdminBeanField[] fields;
	
	protected String title;
	protected FieldGroup fieldGroup;
	protected FormLayout formLayout;
	protected Button btnDelete;
	
	public AdminBeanEditorView(String title, AdminBeanField[] fields) {
		this.title = title;
		this.fields = fields;
		initializeLayout();
	}
	
	public String getTitle() {
		return title;
	}
	
	@Override
	public void displayEntity(Object e) {
		bean = e;
		item = new BeanItem<Object>(bean);
		
		formLayout.removeAllComponents();
		fieldGroup = new FieldGroup(item);
		for (AdminBeanField field : fields) {
			Field<?> f = field.createField(item); 
			formLayout.addComponent(f);
			fieldGroup.bind(f, field.getPropertyId());
		}
	}
	
	@Override
	public boolean isValid() {
		return fieldGroup.isValid();
	}
	
	@Override
	public Object retrieveValidatedEntity() {
		try {
			fieldGroup.commit();
			return item.getBean();
		} catch (CommitException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	protected void initializeLayout() {
		setSizeFull();
		setWidth("100%");
		
		formLayout = new FormLayout();
		formLayout.setSizeFull();
		formLayout.setWidth("100%");
		formLayout.setCaption(title);
		//formLayout.setFooter(footerLayout);
		
		setCompositionRoot(formLayout);
	}
		
	private static final long serialVersionUID = 1L;

}
