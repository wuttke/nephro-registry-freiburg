package eu.wuttke.nrf.domain.subject;

public enum SubjectRelationRole {

	// subject is subject of the relation
	SELF,
	
	// subject is one of the parents of the relation
	CHILD,
	
	// subject has same parent as subject of the relation
	SIBLING
	
}
