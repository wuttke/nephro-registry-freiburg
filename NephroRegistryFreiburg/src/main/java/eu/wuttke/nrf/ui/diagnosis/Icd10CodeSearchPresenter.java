package eu.wuttke.nrf.ui.diagnosis;

import java.util.Collection;

import eu.wuttke.nrf.domain.diagnosis.Icd10Code;
import eu.wuttke.nrf.ui.presenter.SearchPresenter;

public class Icd10CodeSearchPresenter extends SearchPresenter<Icd10Code, Icd10CodeSearchView> {

	public Icd10CodeSearchPresenter() {
		super(new Icd10CodeSearchView());
	}
	
	@Override
	protected Collection<Icd10Code> performSearch(String query) {
		return Icd10Code.findIcd10CodesByNameLike("%" + query + "%").getResultList(); //$NON-NLS-2$ //$NON-NLS-1$
	}

}
