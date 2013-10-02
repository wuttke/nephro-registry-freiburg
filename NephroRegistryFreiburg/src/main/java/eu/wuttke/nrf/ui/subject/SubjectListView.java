package eu.wuttke.nrf.ui.subject;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Button.ClickListener;

import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.ui.component.EditableListComposite;
import eu.wuttke.nrf.ui.misc.StringToShortDateConverter;

public class SubjectListView 
extends EditableListComposite<Subject> {
		
	public SubjectListView() {
		super(Subject.class);
	}
	
	public void addOpenAction(final ClickListener listener) {
		addButton("Open", listener, true);
		addTableItemClickListener(new ItemClickListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void itemClick(ItemClickEvent event) {
				if (event.isDoubleClick())
					listener.buttonClick(null);
			}
		});
	}
	
	public void addGoAdminAction(final ClickListener listener) {
		addButton("Admin", listener, false);
	}
	
	public void searchFieldChanged() {
		
	}

	@Override
	protected void initTable() {
		getTable().setVisibleColumns(new String[] {"patientId", "pseudonym", "lastName", "firstName", "birthdate", "gender"});
		getTable().setColumnHeaders(new String[] {"Patient Id", "Pseudonym", "Last Name", "First Name", "Birthdate", "Gender"});
		getTable().setConverter("birthdate", new StringToShortDateConverter());
	}
		
	private static final long serialVersionUID = 1L;

}
