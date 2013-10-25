package eu.wuttke.nrf.ui.subject;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;

import eu.wuttke.nrf.domain.subject.Relation;
import eu.wuttke.nrf.ui.component.EditableListComposite;

public class RelationListView 
extends EditableListComposite<Relation> {
		
	private Button downloadPedButton;
	
	public RelationListView() {
		super(Relation.class);
	}
	
	public void addDownloadPedAction(ClickListener listener) {
		downloadPedButton = addButton("PED file", listener, false);		
	}
	
	@Override
	protected void initTable() {
		getTable().setVisibleColumns(new String[] {"role", "subject", "father", "mother"});
		getTable().setColumnHeaders(new String[] {"Role", "Person", "Father", "Mother"});
		/*addButton("Pedigree", new ClickListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				// ...
			}
		}, false);*/
	}
	
	public Button getDownloadPedButton() {
		return downloadPedButton;
	}
		
	private static final long serialVersionUID = 1L;

}
