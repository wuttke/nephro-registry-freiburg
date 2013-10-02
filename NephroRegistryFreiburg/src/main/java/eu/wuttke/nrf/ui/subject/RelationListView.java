package eu.wuttke.nrf.ui.subject;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import eu.wuttke.nrf.domain.subject.Relation;
import eu.wuttke.nrf.ui.component.EditableListComposite;

public class RelationListView 
extends EditableListComposite<Relation> {
		
	public RelationListView() {
		super(Relation.class);
	}
	
	@Override
	protected void initTable() {
		getTable().setVisibleColumns(new String[] {"role", "subject", "father", "mother"});
		getTable().setColumnHeaders(new String[] {"Role", "Person", "Father", "Mother"});
		addButton("PED file", new ClickListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				// ...
			}
		}, false);
		addButton("Pedigree", new ClickListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				// ...
			}
		}, false);
	}
		
	private static final long serialVersionUID = 1L;

}
