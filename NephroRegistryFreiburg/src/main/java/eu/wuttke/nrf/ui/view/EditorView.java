package eu.wuttke.nrf.ui.view;

import com.vaadin.ui.Component;

public interface EditorView<E>
extends Component {

	public void displayEntity(E e);
	public E retrieveEntity();
	public boolean isValid();
	
}
