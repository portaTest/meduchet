package tesla.meduchet.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import tesla.meduchet.domain.enumeration.DepartmentType;

@Entity
@Table(name="T_Department")
public class Department {


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="address")
	private String address;
	
	@Column(name="type")
	private DepartmentType departmentType;
	
	@OneToMany(targetEntity=Operation.class,mappedBy="department")
	private List<Operation> operations;
}
