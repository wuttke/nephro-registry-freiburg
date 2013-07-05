package eu.wuttke.nrf.ui.admin;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;

import eu.wuttke.nrf.ui.component.OkCancelWindow;
import eu.wuttke.nrf.ui.misc.BeanUtil;
import eu.wuttke.nrf.ui.presenter.EditorPresenter;
import eu.wuttke.nrf.ui.presenter.RefreshablePresenter;

@Configurable(autowire=Autowire.BY_NAME)
public class AdminBeanEditorPresenter
extends EditorPresenter<Object, AdminBeanEditorView> {

	private Class<?> entityClass;
	
	@javax.persistence.PersistenceContext
	private EntityManager entityManager;
	
	public AdminBeanEditorPresenter(Class<?> entityClass, String title, 
			AdminBeanField[] fields, RefreshablePresenter parent) {
		super(new AdminBeanEditorView(title, fields), parent);
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
		entityManager.merge(entity);
	}
	
	@Override
	public void showEditorWindow(OkCancelWindow w) {
		w.show(((AdminOverviewPresenter)getParent()).getView().getUI(),
				String.format("Edit %s", getEditorView().getTitle()),
				"600", "400");
	}
	
}
