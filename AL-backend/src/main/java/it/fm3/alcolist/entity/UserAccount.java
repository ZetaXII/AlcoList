package it.fm3.alcolist.entity;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name="USER_ACCOUNT")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserAccount {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonIgnore
	@Column(name = "ID", length = 50, nullable = false, unique = true)
	private long id;
	@Column(name = "NAME", length = 50, nullable = false)
	private String name;
	@Column(name = "SURNAME", length = 50, nullable = false)
	private String surname;
	@ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
	private List<Role> roles;
	@Column(name = "MAIN_ROLE", length = 64, nullable = false)
	private String mainRole;
	@Column(name = "PASSWORD", length = 64, nullable = false)
	private String password;
	@Column(name = "EMAIL", length = 50, nullable = false)
	private String email;

	@Column(name = "UUID", length = 50, nullable = false, unique = true)
	private String uuid;
	@OneToMany(mappedBy="user")
	@JsonIgnore
	private Set<Message> messages;
	
	public UserAccount() {
		super();
	}
	public UserAccount(String name, String surname, List<Role> roles, String mainRole, String password, String email) {
		super();
		this.name = name;
		this.surname = surname;
		this.roles = roles;
		this.mainRole = mainRole;
		this.password = password;
		this.email = email;
		this.uuid = UUID.randomUUID().toString();
	}
	public Set<Message> getMessages() {
		return messages;
	}
	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	public String getMainRole() {
		return mainRole;
	}
	public void setMainRole(String mainRole) {
		this.mainRole = mainRole;
	}
	@Override
	public String toString() {
		return "UserAccount [id=" + id + ", name=" + name + ", surname=" + surname + ", roles=" + roles + ", mainRole="
				+ mainRole + ", password=" + password + ", email=" + email  + ", uuid="
				+ uuid + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserAccount other = (UserAccount) obj;
		return Objects.equals(email, other.email) && id == other.id && Objects.equals(mainRole, other.mainRole)
				&& Objects.equals(messages, other.messages) && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password) && Objects.equals(roles, other.roles)
				&& Objects.equals(surname, other.surname) && Objects.equals(uuid, other.uuid);
	}
	
	
	
	
}
