package eu.wuttke.nrf.ui.presenter;

import com.vaadin.ui.Component;

public interface RefreshablePresenter {

	public void refreshContent();
	public Component getView();
	
}
