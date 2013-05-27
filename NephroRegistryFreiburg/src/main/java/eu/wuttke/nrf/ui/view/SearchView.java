package eu.wuttke.nrf.ui.view;

import java.util.Collection;

import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;

public interface SearchView<E>
extends Component {

	public void displaySearchResult(Collection<? extends E> entities);
	public E getSelectedEntity();
	public String getSearchQuery();
	
	public void addSearchButtonClickListener(ClickListener listener);
	public void addSearchFieldBlurListener(BlurListener listener);
	public void addTableItemClickListener(ItemClickListener listener);
	public void addTableValueChangedListener(ValueChangeListener listener);
	
}
