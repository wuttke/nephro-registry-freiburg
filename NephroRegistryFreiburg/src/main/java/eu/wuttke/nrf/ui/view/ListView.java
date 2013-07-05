package eu.wuttke.nrf.ui.view;

import java.util.Collection;

import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;

public interface ListView<E>
extends Component {

	public void displayEntities(Collection<E> entities);
	public E getSelectedEntity();
	
	public void addNewButtonClickListener(ClickListener listener);
	public void addEditButtonClickListener(ClickListener listener);
	public void addDeleteButtonClickListener(ClickListener listener);
	public void addTableItemClickListener(ItemClickListener listener);
	
}
