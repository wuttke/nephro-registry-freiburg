package eu.wuttke.nrf.ui.subject;

import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.ui.component.EditableListComposite;

public class SubjectListView 
extends EditableListComposite<Subject> {
	
	public SubjectListView() {
		super(Subject.class);
	}
	
	@Override
	protected void initTable() {
		getTable().setVisibleColumns(new String[] {"id", "lastName", "firstName", "birthdate", "gender"});
		//getTable().setColumnHeaders(columnHeaders);
	}
		
	private static final long serialVersionUID = 1L;

}
