package eu.wuttke.nrf.ui.tabs;

import com.vaadin.ui.Component;
import com.vaadin.ui.Panel;

import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.ui.presenter.RefreshablePresenter;

public class LabTabPresenter implements RefreshablePresenter {

		private Component view;
		@SuppressWarnings("unused")
		private Subject parentSubject;

		public LabTabPresenter() {
			view = new Panel("Lab Values");
		}
		
		public void setParentSubject(Subject parentSubject) {
			this.parentSubject = parentSubject;
		}
		
		@Override
		public void refreshContent() {
			// TODO implement and use parentSubject
		}

		@Override
		public Component getView() {
			return view;
		}

}
