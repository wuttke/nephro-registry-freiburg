package eu.wuttke.nrf.ui.subject;

import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.ui.component.SearchListComposite;
import eu.wuttke.nrf.ui.misc.StringToShortDateConverter;

public class SubjectSearchView
extends SearchListComposite<Subject> {

	public SubjectSearchView() {
		super(Subject.class);
	}

	@Override
	protected void initTable() {
		getTable().setVisibleColumns(new String[] {"patientId", "pseudonym", "lastName", "firstName", "birthdate", "gender"});
		getTable().setColumnHeaders(new String[] {"Patient Id", "Pseudonym", "Last Name", "First Name", "Birthdate", "Gender"});
		getTable().setConverter("birthdate", new StringToShortDateConverter());
	}
	
	private static final long serialVersionUID = 1L;

}
