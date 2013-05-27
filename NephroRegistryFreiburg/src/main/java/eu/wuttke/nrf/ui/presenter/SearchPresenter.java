package eu.wuttke.nrf.ui.presenter;

import java.util.Collection;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.Action.Listener;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;

import eu.wuttke.nrf.ui.component.OkCancelWindow;
import eu.wuttke.nrf.ui.view.SearchView;

public abstract class SearchPresenter<E, V extends SearchView<E>> implements RefreshablePresenter {

	private OkCancelWindow window;
	private Listener entityChosenListener;
	private V searchView;
	private String lastSearch = null;
	
	public SearchPresenter(V v) {
		this.searchView = v;
		
		searchView.addSearchButtonClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				doSearch(searchView.getSearchQuery());
			}
		});
		
		searchView.addSearchFieldBlurListener(new BlurListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void blur(BlurEvent event) {
				doSearch(searchView.getSearchQuery());
			}
		});

		searchView.addTableValueChangedListener(new ValueChangeListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void valueChange(ValueChangeEvent event) {
				if (window != null)
					window.setOkEnabled(searchView.getSelectedEntity() != null);
			}
		});
		
		searchView.addTableItemClickListener(new ItemClickListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void itemClick(ItemClickEvent event) {
				if (event.isDoubleClick()) {
					chooseResult(searchView.getSelectedEntity());
					window.close();
				}
			}
		});
	}
	
	public void setEntityChosenListener(Listener entityChosenListener) {
		this.entityChosenListener = entityChosenListener;
	}
	
	protected void doSearch(String query) {
		if (lastSearch == null || !lastSearch.equals(query)) {
			lastSearch = query;
			Collection<? extends E> result = performSearch(query);
			searchView.displaySearchResult(result);
		}
	}
	
	protected abstract Collection<? extends E> performSearch(String query);

	// may be overriden to capture result
	protected void chooseResult(E selectedEntity) {
		if (entityChosenListener != null)
			entityChosenListener.handleAction(this, selectedEntity);
	}

	public void showSearchWindow(Component parent) {
		window = new OkCancelWindow(getView()) {
			private static final long serialVersionUID = 1L;
			@Override
			protected void onSave() {
				chooseResult(searchView.getSelectedEntity());
			}
		};
		window.setOkEnabled(false);
		showWindow(window, parent);
	}

	// may be overriden to adjust title/size
	protected void showWindow(OkCancelWindow w, Component parent) {
		w.show(parent.getUI(), "Search", "800", "600");	
	}

	@Override
	public Component getView() {
		return searchView;
	}
	
	public void refreshContent() {
		// initiale Belegung?
	}
	
}
