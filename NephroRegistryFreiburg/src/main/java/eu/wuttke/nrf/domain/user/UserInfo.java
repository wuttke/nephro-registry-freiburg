package eu.wuttke.nrf.domain.user;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;

@RooJpaEntity
@RooJavaBean
public class UserInfo {

	@Id
	@NotNull
	@Column(length=32)
	private String userName;
	
	@Column(length=32)
	@NotNull
	private String password;

	@Column(length=80)
	@NotNull
	private String lastName;

	@Column(length=80)
	@NotNull
	private String firstName;

	@Column(length=40)
	@NotNull
	private String title;
	
	@Column(length=80)
	@NotNull
	private String email;	
	
	@ElementCollection(targetClass=UserRole.class)
	@JoinTable(name="UserRole", joinColumns=@JoinColumn(name="username"))
	@Column(name="role", nullable=false, length=40)
	@Enumerated(EnumType.STRING)
	private List<UserRole> roles;
	
}
