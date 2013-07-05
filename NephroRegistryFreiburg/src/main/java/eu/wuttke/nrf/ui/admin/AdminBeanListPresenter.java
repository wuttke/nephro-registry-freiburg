package eu.wuttke.nrf.ui.admin;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;

import eu.wuttke.nrf.ui.presenter.EditorPresenter;
import eu.wuttke.nrf.ui.presenter.ListPresenter;
import eu.wuttke.nrf.ui.presenter.RefreshablePresenter;

@Configurable(autowire=Autowire.BY_NAME)
public class AdminBeanListPresenter
extends ListPresenter<Object, AdminBeanListView>{

	private Class<?> entityClass;
	private String title;
	private AdminBeanField[] detailFields;
	private RefreshablePresenter parent;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public AdminBeanListPresenter(Class<?> entityClass, 
			String title,
			String[] visibleColumns, 
			String[] columnHeaders, 
			float[] columnWeights,
			AdminBeanField[] detailFields,
			RefreshablePresenter parent) {
		super(new AdminBeanListView(entityClass, visibleColumns, columnHeaders, columnWeights));
		
		this.title = title;
		this.entityClass = entityClass;
		this.detailFields = detailFields;
		this.parent = parent;
	}
	
	public String getTitle() {
		return title;
	}
	
	@Override
	public EditorPresenter<Object, ?> createEditorPresenter() {
		return new AdminBeanEditorPresenter(entityClass, title, detailFields, parent);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Object> loadEntities() {
		TypedQuery<?> query = entityManager.createQuery("FROM " + entityClass.getSimpleName(), entityClass);
		return (Collection<Object>)query.getResultList();
	}
	
}
