package eu.wuttke.nrf.domain.medication;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(versionField = "", finders = { "findMedicamentsByTradenameLike" })
public class Medicament {

    @Id
    @NotNull
    private int mid;

    @Column(length = 800)
    private String tradename;

    @Column(length = 12)
    private String atcCode;

    @Column(length = 200)
    private String offerer;

    @Column(length = 20)
    private String pharmaceuticalForm;
}
