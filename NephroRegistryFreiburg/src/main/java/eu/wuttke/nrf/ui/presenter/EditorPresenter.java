package eu.wuttke.nrf.ui.presenter;

import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.ui.UI;

import eu.wuttke.nrf.ui.component.OkCancelWindow;
import eu.wuttke.nrf.ui.view.EditorView;

public abstract class EditorPresenter<E, V extends EditorView<E>> {

	private E entity;
	private V editorView;
	private RefreshablePresenter parent;
	
	public EditorPresenter(V editorView, RefreshablePresenter parent) {
		this.editorView = editorView;
		this.parent = parent;
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
	
	public void deleteEntity(final E entity) {
		// ask and do it
		ConfirmDialog.show(findUI(), 
				"Are you sure you want to delete the selected record?",
		        new ConfirmDialog.Listener() {
					private static final long serialVersionUID = 1L;
					public void onClose(ConfirmDialog dialog) {
		                if (dialog.isConfirmed()) {
		                    realDeleteEntity(entity);
		                    refreshParent();
		                }
		            }
		        });
	}
	
	public abstract void realDeleteEntity(E entity);
	
	public abstract UI findUI();
	
	protected void refreshParent() {
		if (parent != null)
			parent.refreshContent();
	}
	
	protected boolean validateEntity(E entity) {
		return true;
	}

	public void showEntityWindow(E entity) {
		OkCancelWindow w = new OkCancelWindow(getEditorView()) {
			private static final long serialVersionUID = 1L;
			@Override
			protected boolean isContentValid() {
				if (getEditorView().isValid()) {
					return validateEntity(getEditorView().retrieveValidatedEntity());
				} else
					return false;
			}
			@Override
			protected void onSave() {
				E entity2 = getEditorView().retrieveValidatedEntity();
				saveEntity(entity2);
				refreshParent();
			}
		};
		
		this.entity = entity;
		showEditorWindow(w);
		displayEntity();
	}

	public E getEntity() {
		return entity;
	}

	public abstract void showEditorWindow(OkCancelWindow w);
	public abstract E createEntity();
	public abstract void saveEntity(E entity);
	
	protected void displayEntity() {
		getEditorView().displayEntity(entity);
	}
	
}
