package eu.wuttke.nrf.ui.presenter;

import eu.wuttke.nrf.ui.component.OkCancelWindow;
import eu.wuttke.nrf.ui.view.EditorView;

public abstract class EditorPresenter<E, V extends EditorView<E>> {

	private V editorView;
	private RefreshablePresenter parent;
	
	public EditorPresenter(V editorView, RefreshablePresenter parent) {
		this.editorView = editorView;
		this.parent = parent;
		editorView.setEditorPresenter(this);
	}
	
	public V getEditorView() {
		return editorView;
	}
	
	public RefreshablePresenter getParent() {
		return parent;
	}
	
	public void newEntity() {
		showEntityWindow(createEntity());
	}
	
	public void editEntity(E entity) {
		showEntityWindow(entity);
	}
	
	public void deleteEntity(E entity) {
		// ask and do it
	}
	
	protected void refreshParent() {
		if (parent != null)
			parent.refreshContent();
	}

	public void showEntityWindow(E entity) {
		OkCancelWindow w = new OkCancelWindow(getEditorView()) {
			private static final long serialVersionUID = 1L;
			@Override
			protected boolean isContentValid() {
				if (getEditorView().isValid())
					return super.isContentValid();
				else
					return false;
			}
			@Override
			protected void onSave() {
				E entity2 = getEditorView().retrieveEntity();
				saveEntity(entity2);
				refreshParent();
			}
		};
		getEditorView().displayEntity(entity);
		showEditorWindow(w);
	}

	public abstract void showEditorWindow(OkCancelWindow w);
	public abstract E createEntity();
	public abstract void saveEntity(E entity);
	
}
