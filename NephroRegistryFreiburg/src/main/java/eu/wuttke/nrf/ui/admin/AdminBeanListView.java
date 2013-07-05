package eu.wuttke.nrf.ui.admin;

import eu.wuttke.nrf.ui.component.EditableListComposite;

//@Configurable(autowire=Autowire.BY_NAME)
public class AdminBeanListView 
extends EditableListComposite<Object> {
	
	private String[] visibleColumns; 
	private String[] columnHeaders; 
	private float[] columnWeights;
	
	@SuppressWarnings("unchecked")
	public AdminBeanListView(Class<?> entityClass, 
			String[] visibleColumns, 
			String[] columnHeaders, 
			float[] columnWeights) {
		super((Class<Object>)entityClass);
		
		this.visibleColumns = visibleColumns;
		this.columnHeaders = columnHeaders;
		this.columnWeights = columnWeights;
		
		assert visibleColumns.length == columnHeaders.length;
		assert columnHeaders.length == columnWeights.length;
	}

	@Override
	protected void initTable() {
		getTable().setVisibleColumns(visibleColumns);
		getTable().setColumnHeaders(columnHeaders);

		for (int i = 0; i < columnWeights.length; i++)
			getTable().setColumnExpandRatio(visibleColumns[i], columnWeights[i]);
	}

	private static final long serialVersionUID = 1L;
	
}
