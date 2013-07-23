package eu.wuttke.nrf.ui.subject;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

public class SubjectDetailView extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private TabSheet tabSheet;
	@AutoGenerated
	private Panel headerPanel;
	@AutoGenerated
	private HorizontalLayout headerPanelLayout;
	@AutoGenerated
	private Button btnNext;
	@AutoGenerated
	private Button btnPrev;
	@AutoGenerated
	private Button btnBack;
	@AutoGenerated
	private Label lblTitle;
	private static final long serialVersionUID = 1L;
	public TabSheet getTabSheet() {
		return tabSheet;
	}
	
	public void addBackButtonClickListener(ClickListener listener) {
		btnBack.addClickListener(listener);
	}

	public void addNextButtonClickListener(ClickListener listener) {
		btnNext.addClickListener(listener);
	}
	
	public void addPrevButtonClickListener(ClickListener listener) {
		btnPrev.addClickListener(listener);
	}
	
	public SubjectDetailView() {
		buildMainLayout();
		setCompositionRoot(mainLayout);
	}

	public void setTitle(String title) {
		lblTitle.setValue(title);
	}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(false);
		mainLayout.setSpacing(true);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		// headerPanel
		headerPanel = buildHeaderPanel();
		mainLayout.addComponent(headerPanel);
		
		// tabSheet
		tabSheet = new TabSheet();
		tabSheet.setImmediate(false);
		tabSheet.setWidth("100.0%");
		tabSheet.setHeight("100.0%");
		mainLayout.addComponent(tabSheet);
		mainLayout.setExpandRatio(tabSheet, 1.0f);
		
		return mainLayout;
	}

	@AutoGenerated
	private Panel buildHeaderPanel() {
		// common part: create layout
		headerPanel = new Panel();
		headerPanel.setImmediate(false);
		headerPanel.setWidth("100.0%");
		headerPanel.setHeight("70px");
		
		// headerPanelLayout
		headerPanelLayout = buildHeaderPanelLayout();
		headerPanel.setContent(headerPanelLayout);
		
		return headerPanel;
	}

	@AutoGenerated
	private HorizontalLayout buildHeaderPanelLayout() {
		// common part: create layout
		headerPanelLayout = new HorizontalLayout();
		headerPanelLayout.setImmediate(false);
		headerPanelLayout.setWidth("100.0%");
		headerPanelLayout.setHeight("100.0%");
		headerPanelLayout.setMargin(true);
		headerPanelLayout.setSpacing(true);
		
		// lblTitle
		lblTitle = new Label();
		lblTitle.setImmediate(false);
		lblTitle.setWidth("-1px");
		lblTitle.setHeight("-1px");
		lblTitle.setValue("Titel der Ansicht");
		headerPanelLayout.addComponent(lblTitle);
		headerPanelLayout.setExpandRatio(lblTitle, 1.0f);
		headerPanelLayout.setComponentAlignment(lblTitle, new Alignment(33));
		
		// btnBack
		btnBack = new Button();
		btnBack.setCaption("Back");
		btnBack.setImmediate(true);
		btnBack.setWidth("80px");
		btnBack.setHeight("-1px");
		headerPanelLayout.addComponent(btnBack);
		headerPanelLayout.setComponentAlignment(btnBack, new Alignment(34));
		
		// btnPrev
		btnPrev = new Button();
		btnPrev.setCaption("Prev");
		btnPrev.setImmediate(false);
		btnPrev.setWidth("80px");
		btnPrev.setHeight("-1px");
		headerPanelLayout.addComponent(btnPrev);
		headerPanelLayout.setComponentAlignment(btnPrev, new Alignment(34));
		
		// btnNext
		btnNext = new Button();
		btnNext.setCaption("Next");
		btnNext.setImmediate(false);
		btnNext.setWidth("80px");
		btnNext.setHeight("-1px");
		headerPanelLayout.addComponent(btnNext);
		headerPanelLayout.setComponentAlignment(btnNext, new Alignment(34));
		
		return headerPanelLayout;
	}

}
