package eu.wuttke.nrf.controller;

public interface EditorController<E> {

	public void newEntity();
	public void changeEntity(E e);
	public void deleteEntity(E e);
	
}
