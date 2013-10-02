package eu.wuttke.nrf.ui.subject;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.javabean.RooJavaBean;

import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.domain.subject.SubjectRepository;
import eu.wuttke.nrf.ui.presenter.SearchPresenter;

@Configurable
@RooJavaBean
public class SubjectSearchPresenter extends
		SearchPresenter<Subject, SubjectSearchView> {

	public SubjectSearchPresenter() {
		super(new SubjectSearchView());
	}

	@Override
	protected Collection<Subject> performSearch(String query) {
		return subjectRepository.performSubjectSearch(query);
	}
	
	@Autowired
	private SubjectRepository subjectRepository;

}
