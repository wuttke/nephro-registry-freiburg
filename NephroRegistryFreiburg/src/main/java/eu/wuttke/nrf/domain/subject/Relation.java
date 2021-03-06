package eu.wuttke.nrf.domain.subject;

import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@Audited
@RooJpaActiveRecord(finders = { "findRelationsBySubject", "findRelationsByFather", "findRelationsByMother" })
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"subject_id"})})
public class Relation {

    @NotNull
    @ManyToOne
    private Subject subject;

    @ManyToOne
    private Subject mother;

    @ManyToOne
    private Subject father;
    
    @Transient
    private SubjectRelationRole role;
    
    @Override
    public boolean equals(Object obj) {
    	assert obj instanceof Relation;
    	return ((Relation)obj).getSubject().getId().longValue() ==
    			getSubject().getId().longValue();
    }
    
}
