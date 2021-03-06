package eu.wuttke.nrf.domain.subject;

import eu.wuttke.nrf.domain.misc.PrecisionDate;
import eu.wuttke.nrf.domain.misc.PrecisionDateUtil;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.envers.Audited;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@Audited
@RooJpaActiveRecord(finders = { "findSubjectsByLastNameLike" })
public class Subject {

    @NotNull
    @Column(length = 32)
    private String patientId;

    @NotNull
    @Column(length = 128)
    private String lastName;

    @NotNull
    @Column(length = 128)
    private String firstName;

    @NotNull
    @Column(length = 64)
    private String title;

    @Column(length = 64)
    private String birthName;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date birthdate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 16)
    private Gender gender;

    @NotNull
    @Column(length = 8)
    private String pseudonym;

    @NotNull
    private boolean death;

    @Embedded
    private PrecisionDate dateOfDeath;

    public void generatePseudonym() {
        pseudonym = RandomStringUtils.randomAlphanumeric(6).toUpperCase();
    }

    public String formatFullName() {
        if (StringUtils.isNotEmpty(getTitle())) return String.format("%s %s, %s", getTitle(), getLastName(), getFirstName()); else return String.format("%s, %s", getLastName(), getFirstName());
    }

    @Override
    public String toString() {
        String bd = PrecisionDateUtil.formatDate(getBirthdate(), null);
        if (StringUtils.isNotEmpty(getTitle())) return String.format("%s %s, %s (*%s)", getTitle(), getLastName(), getFirstName(), bd); else return String.format("%s, %s (*%s)", getLastName(), getFirstName(), bd);
    }
}
