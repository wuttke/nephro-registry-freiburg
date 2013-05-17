package eu.wuttke.nrf.ui.subject;

import eu.wuttke.nrf.domain.subject.Relation;
import eu.wuttke.nrf.ui.component.EditableListComposite;

public class RelationListView 
extends EditableListComposite<Relation> {
		
	public RelationListView() {
		super(Relation.class);
	}
	
	@Override
	protected void initTable() {
		getTable().setVisibleColumns(new String[] {"subject", "father", "mother"});
		getTable().setColumnHeaders(new String[] {"Person", "Father", "Mother"});
	}
		
	private static final long serialVersionUID = 1L;

}
