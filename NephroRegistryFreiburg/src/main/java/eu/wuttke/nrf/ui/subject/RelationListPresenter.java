package eu.wuttke.nrf.ui.subject;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import eu.wuttke.nrf.domain.subject.Relation;
import eu.wuttke.nrf.domain.subject.RelationRepository;
import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.ui.presenter.EditorPresenter;
import eu.wuttke.nrf.ui.presenter.ListPresenter;

@Configurable
public class RelationListPresenter 
extends ListPresenter<Relation, RelationListView> {
	
	private Subject subject;
	
	public RelationListPresenter() {
		super(new RelationListView());
	}
	
	public void setParentSubject(Subject parentSubject) {
		this.subject = parentSubject;
	}
	
	@Override
	public Collection<Relation> loadEntities() {
		return relationRepository.findFirstDegreeFamily(subject);
	}
	
	@Override
	public EditorPresenter<Relation, ?> createEditorPresenter() {
		return new RelationEditorPresenter(subject, this);
	}
	
	public void setRelationRepository(RelationRepository relationRepository) {
		this.relationRepository = relationRepository;
	}

	@Autowired
	private RelationRepository relationRepository;
	
}
