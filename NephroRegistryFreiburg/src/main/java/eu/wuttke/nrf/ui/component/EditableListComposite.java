package eu.wuttke.nrf.ui.component;

import java.util.Collection;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class EditableListComposite<E>
extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private HorizontalLayout horizontalLayoutFooter;

	@AutoGenerated
	private Button btnDelete;

	@AutoGenerated
	private Button btnEdit;

	@AutoGenerated
	private Button btnNew;

	@AutoGenerated
	private Table table;

	private BeanContainer<Long, E> container;
	private Class<? super E> entityClass;

	public EditableListComposite(Class<? super E> entityClass) {
		this.entityClass = entityClass;
		
		buildMainLayout();
		setCompositionRoot(mainLayout);
		
		btnEdit.setEnabled(false);
		btnDelete.setEnabled(false);
		
		table.addValueChangeListener(new Property.ValueChangeListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void valueChange(ValueChangeEvent event) {
				btnEdit.setEnabled(table.getValue() != null);
				btnDelete.setEnabled(table.getValue() != null);
			}
		});
		
		btnNew.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				newEntity();
			}
		});
		
		btnEdit.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("unchecked")
			@Override
			public void buttonClick(ClickEvent event) {
				changeEntity((E)table.getValue());
			}
		});
		
		btnDelete.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("unchecked")
			@Override
			public void buttonClick(ClickEvent event) {
				deleteEntity((E)table.getValue());
			}
		});
	}
	
	public void displayEntities(Collection<? extends E> entities) {
		container = new BeanContainer<Long, E>(entityClass);
		container.setBeanIdProperty("id");
		container.addAll(entities);
		table.setContainerDataSource(container);
		initTable();
	}
	
	protected Table getTable() {
		return table;
	}
	
	protected void initTable() {
		//table.setVisibleColumns(visibleColumns);
		//table.setColumnHeaders(columnHeaders);
	}

	protected void newEntity() {
		
	}
	
	protected void changeEntity(E item) {
		
	}

	protected void deleteEntity(E item) {
		
	}
	
	private static final long serialVersionUID = 1L;
	
	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		// table
		table = new Table();
		table.setImmediate(false);
		table.setWidth("100.0%");
		table.setHeight("100.0%");
		mainLayout.addComponent(table);
		mainLayout.setExpandRatio(table, 1.0f);
		
		// horizontalLayoutFooter
		horizontalLayoutFooter = buildHorizontalLayoutFooter();
		mainLayout.addComponent(horizontalLayoutFooter);
		
		return mainLayout;
	}

	@AutoGenerated
	private HorizontalLayout buildHorizontalLayoutFooter() {
		// common part: create layout
		horizontalLayoutFooter = new HorizontalLayout();
		horizontalLayoutFooter.setImmediate(false);
		horizontalLayoutFooter.setWidth("-1px");
		horizontalLayoutFooter.setHeight("-1px");
		horizontalLayoutFooter.setMargin(false);
		horizontalLayoutFooter.setSpacing(true);
		
		// btnNew
		btnNew = new Button();
		btnNew.setCaption("New");
		btnNew.setImmediate(true);
		btnNew.setWidth("80px");
		btnNew.setHeight("-1px");
		horizontalLayoutFooter.addComponent(btnNew);
		
		// btnEdit
		btnEdit = new Button();
		btnEdit.setCaption("Edit");
		btnEdit.setImmediate(true);
		btnEdit.setWidth("80px");
		btnEdit.setHeight("-1px");
		horizontalLayoutFooter.addComponent(btnEdit);
		
		// btnDelete
		btnDelete = new Button();
		btnDelete.setCaption("Delete");
		btnDelete.setImmediate(true);
		btnDelete.setWidth("80px");
		btnDelete.setHeight("-1px");
		horizontalLayoutFooter.addComponent(btnDelete);
		
		return horizontalLayoutFooter;
	}

}
