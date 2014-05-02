package eu.wuttke.nrf.ui.admin;

import eu.wuttke.nrf.ui.component.OkCancelWindow;
import eu.wuttke.nrf.ui.misc.BeanUtil;
import eu.wuttke.nrf.ui.presenter.EditorPresenter;
import eu.wuttke.nrf.ui.presenter.RefreshablePresenter;

public class AdminBeanEditorPresenter
extends EditorPresenter<Object, AdminBeanEditorView> {

	private Class<?> entityClass;
	private int fieldCount;
	
	public AdminBeanEditorPresenter(Class<?> entityClass, String title, 
			AdminBeanField[] fields, RefreshablePresenter parent) {
		super(new AdminBeanEditorView(title, fields), parent);
		this.fieldCount = fields.length;
		this.entityClass = entityClass;
	}

	@Override
	public Object createEntity() {
		try {
			Object o = entityClass.newInstance();
			initEmptyBean(o);
			return o;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private void initEmptyBean(Object object) {
		// set String properties to "empty" instead of null (if not a reference)
		// (this can be done more efficiently)
		for (String propertyId : BeanUtil.getBeanPropertiesByType(object.getClass(), String.class)) {
			if (BeanUtil.getPropertyValue(object, propertyId) == null)
				BeanUtil.setPropertyValue(object, propertyId, "");
		}
	}
	
	@Override
	public void saveEntity(Object entity) {
		try {
			entityClass.getMethod("merge").invoke(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void realDeleteEntity(Object entity) {
		try {
			entityClass.getMethod("remove").invoke(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void showEditorWindow(OkCancelWindow w) {
		int height = 30 * fieldCount + 160;
		w.show(((AdminOverviewPresenter)getParent()).getView().getUI(),
				String.format("Edit %s", getEditorView().getTitle()),
				"600", Integer.toString(height));
	}
	
}
