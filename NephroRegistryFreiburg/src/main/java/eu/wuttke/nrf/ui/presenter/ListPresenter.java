package eu.wuttke.nrf.ui.presenter;

import java.util.Collection;

import eu.wuttke.nrf.ui.view.ListView;

public abstract class ListPresenter<E, V extends ListView<E>>
implements RefreshablePresenter {

	private V listView;
	
	public ListPresenter(V listView) {
		this.listView = listView;
	}
	
	public V getListView() {
		return listView;
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
