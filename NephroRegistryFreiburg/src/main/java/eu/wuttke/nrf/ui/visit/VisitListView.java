package eu.wuttke.nrf.ui.visit;

import eu.wuttke.nrf.domain.visit.Visit;
import eu.wuttke.nrf.ui.component.EditableListComposite;
import eu.wuttke.nrf.ui.misc.StringToShortDateConverter;

public class VisitListView 
extends EditableListComposite<Visit> {
		
	public VisitListView() {
		super(Visit.class);
	}
	
	@Override
	protected void initTable() {
		getTable().setVisibleColumns(new String[] {"visitDate"});
		getTable().setColumnHeaders(new String[] {"Visit Date"});
		getTable().setConverter("visitDate", new StringToShortDateConverter());
	}
		
	private static final long serialVersionUID = 1L;

}
