package eu.wuttke.nrf.ui.presenter;

import java.util.Collection;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;

import eu.wuttke.nrf.ui.view.ListView;

public abstract class ListPresenter<E, V extends ListView<E>>
implements RefreshablePresenter {

	private V listView;
	
	public ListPresenter(V listView) {
		this.listView = listView;
		addViewActions();
	}
	
	protected void addViewActions() {
		listView.addNewButtonClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				newEntity();
			}
		});
		
		listView.addEditButtonClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				editEntity(getListView().getSelectedEntity());
			}
		});
		
		listView.addDeleteButtonClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				deleteEntity(getListView().getSelectedEntity());
			}
		});

		addDoubleClickHandler();
	}

	protected void addDoubleClickHandler() {
		listView.addTableItemClickListener(new ItemClickListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void itemClick(ItemClickEvent event) {
				if (event.isDoubleClick())
					editEntity(getListView().getSelectedEntity());
			}
		});
	}

	public V getListView() {
		return listView;
	}
	
	public void setListView(V listView) {
		this.listView = listView;
		addViewActions();
	}
	
	@Override
	public Component getView() {
		return getListView();
	}
	
	public abstract Collection<? extends E> loadEntities();
	public abstract EditorPresenter<E, ?> createEditorPresenter();
	
	public void refreshContent() {
		listView.displayEntities(loadEntities());
	}
	
	public void newEntity() {
		createEditorPresenter().newEntity();
	}
	
	public void editEntity(E s) {
		createEditorPresenter().editEntity(s);
	}

	public void deleteEntity(E s) {
		createEditorPresenter().deleteEntity(s);
	}

}
