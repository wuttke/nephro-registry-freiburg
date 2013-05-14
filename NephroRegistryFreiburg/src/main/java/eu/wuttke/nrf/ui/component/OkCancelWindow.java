package eu.wuttke.nrf.ui.component;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class OkCancelWindow 
extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private HorizontalLayout horizontalLayoutButtons;

	@AutoGenerated
	private Button btnCancel;

	@AutoGenerated
	private Button btnOk;

	private static final long serialVersionUID = 1L;

	private Window window;
	
	public OkCancelWindow(Component innerComponent) {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		mainLayout.addComponent(innerComponent, 0);
		mainLayout.setExpandRatio(innerComponent, 1f);
		
		btnOk.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;
			public void buttonClick(ClickEvent event) {
				okClicked();
			}
		});
		
		btnCancel.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;
			public void buttonClick(ClickEvent event) {
				cancelClicked();
			}
		});
	}
	
	public void show(UI ui, String caption, String width, String height) {
		window = new Window(caption);
		window.setWidth(width);
		window.setHeight(height);
		window.setContent(this);
		window.setModal(true);
		window.center();
		ui.addWindow(window);
	}
	
	protected void cancelClicked() {
		window.close();
	}
	
	protected void okClicked() {
		if (isContentValid()) {
			window.close();
		}
	}

	protected boolean isContentValid() {
		return true;
	}

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
		
		// horizontalLayoutButtons
		horizontalLayoutButtons = buildHorizontalLayoutButtons();
		mainLayout.addComponent(horizontalLayoutButtons);
		mainLayout.setComponentAlignment(horizontalLayoutButtons,
				new Alignment(6));
		
		return mainLayout;
	}

	@AutoGenerated
	private HorizontalLayout buildHorizontalLayoutButtons() {
		// common part: create layout
		horizontalLayoutButtons = new HorizontalLayout();
		horizontalLayoutButtons.setImmediate(false);
		horizontalLayoutButtons.setWidth("-1px");
		horizontalLayoutButtons.setHeight("-1px");
		horizontalLayoutButtons.setMargin(false);
		horizontalLayoutButtons.setSpacing(true);
		
		// btnOk
		btnOk = new Button();
		btnOk.setCaption("OK");
		btnOk.setImmediate(true);
		btnOk.setWidth("80px");
		btnOk.setHeight("-1px");
		horizontalLayoutButtons.addComponent(btnOk);
		horizontalLayoutButtons.setExpandRatio(btnOk, 1.0f);
		horizontalLayoutButtons.setComponentAlignment(btnOk, new Alignment(34));
		
		// btnCancel
		btnCancel = new Button();
		btnCancel.setCaption("Cancel");
		btnCancel.setImmediate(true);
		btnCancel.setWidth("80px");
		btnCancel.setHeight("-1px");
		horizontalLayoutButtons.addComponent(btnCancel);
		horizontalLayoutButtons.setComponentAlignment(btnCancel, new Alignment(
				34));
		
		return horizontalLayoutButtons;
	}

}
