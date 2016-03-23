package tesla.meduchet.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import tesla.meduchet.domain.enumeration.Role;
import tesla.meduchet.domain.enumeration.Sex;

@Entity
@Table(name = "T_User")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "first_name", length = 32)
	private String firsName;

	@Column(name = "last_name", length = 32)
	private String lastName;

	@Column(name = "middle_name", length = 32)
	private String middleName;

	@Temporal(TemporalType.DATE)
	@Column(name = "birth_date")
	private Date birthDate;

	@Column(name = "sex")
	private Sex sex;

	@OneToMany(targetEntity = Phone.class, mappedBy = "user")
	private List<Phone> phones;

	@Column(name = "user_role")
	private Role role;

	@Column(name = "login", length = 32)
	private String login;

	@Column(name = "password")
	private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirsName() {
		return firsName;
	}

	public void setFirsName(String firsName) {
		this.firsName = firsName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}