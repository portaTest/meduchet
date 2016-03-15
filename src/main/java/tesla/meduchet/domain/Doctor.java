package tesla.meduchet.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="T_Doctor")
public class Doctor {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="fee")
	private BigDecimal fee;
	
	@Column(name="work",length=128)
	private String work;
	
	@Column(name="balance")
	private BigDecimal balance;
	
	@OneToOne(targetEntity=User.class)
	private User user;
	
	@OneToMany(targetEntity=Record.class,mappedBy="doctor")
	private List<Record> records;
	
	
}
