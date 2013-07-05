package eu.wuttke.nrf.domain.encounter;

import eu.wuttke.nrf.domain.attribute.CategoryBase;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.hibernate.envers.Audited;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord
@RooToString
@Audited
public class EncounterCategory extends CategoryBase {

    @ManyToOne
    @NotNull
    private Encounter encounter;

}
