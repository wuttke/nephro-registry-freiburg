package eu.wuttke.nrf.ui.view;

import java.util.Collection;

import com.vaadin.ui.Component;

import eu.wuttke.nrf.ui.presenter.ListPresenter;

public interface ListView<E>
extends Component {

	public ListPresenter<E, ?> getListPresenter();
	public void setListPresenter(ListPresenter<E, ?> presenter);
	public void displayEntities(Collection<? extends E> entities);
	
}
