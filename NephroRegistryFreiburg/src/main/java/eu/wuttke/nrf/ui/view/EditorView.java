package eu.wuttke.nrf.ui.view;

import com.vaadin.ui.Component;

import eu.wuttke.nrf.ui.presenter.EditorPresenter;

public interface EditorView<E>
extends Component {

	public EditorPresenter<E, ?> getEditorPresenter();
	public void setEditorPresenter(EditorPresenter<E, ?> presenter);
	
	public void displayEntity(E e);
	public E retrieveEntity();
	
}
