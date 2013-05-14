package eu.wuttke.nrf.domain.audit;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RevisionEntity(EnversSpringSecurityRevisionListener.class)
@RooJavaBean
@RooToString
@Entity
public class RevisionInfo {

	@Id
	@RevisionNumber
	@GeneratedValue
	private Integer id;

	@RevisionTimestamp
	private long timestamp;

	@NotNull
	@Column(length=64)
	private String username;

	@Transient
	public Date getRevisionDate() {
		return new Date(timestamp);
	}

	public int hashCode() {
		int result;
		result = id != null ? id.intValue() : 0;
		result = 31 * result + (int) (timestamp ^ (timestamp >>> 32));
		return result;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof RevisionInfo))
			return false;
	
		RevisionInfo that = (RevisionInfo) o;
	
		if (id != that.id)
			return false;
		if ((id == null || that.id == null) && (id != null || that.id != null))
			return false;
		if (id != null && that.id != null && id.intValue() != that.id.intValue())
			return false;
		if (timestamp != that.timestamp)
			return false;
		if (!username.equals(that.username))
			return false;
	
		return true;
	}

}
