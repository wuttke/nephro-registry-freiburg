package eu.wuttke.nrf.controller;

import java.util.Collection;

import eu.wuttke.nrf.domain.subject.Subject;

/**
 * List controller without filters, keeping all entities in memory.
 * @author Wuttke
 */
public interface SimpleListController<E> 
extends Controller {

	public Collection<? extends E> loadEntities();
	public Class<? super Subject> getEntityClass();
	
}
