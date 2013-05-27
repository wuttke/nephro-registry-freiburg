package eu.wuttke.nrf.ui.medication;

import java.util.Collection;

import eu.wuttke.nrf.domain.medication.Medicament;
import eu.wuttke.nrf.ui.presenter.SearchPresenter;

public class MedicamentSearchPresenter extends SearchPresenter<Medicament, MedicamentSearchView> {

	public MedicamentSearchPresenter() {
		super(new MedicamentSearchView());
	}
	
	@Override
	protected Collection<Medicament> performSearch(String query) {
		return Medicament.findMedicamentsByTradenameLike(query + "%").getResultList();
	}

}
