package tesla.meduchet.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "T_Doctor")
public class Doctor extends User {


	@Column(name = "specialisation")
	private String specialisation;

	@Column(name = "type")
	private String type;

	@Column(name = "work", length = 128)
	private String work;

	@Column(name = "balance")
	private BigDecimal balance;


	@OneToMany(targetEntity = Record.class, mappedBy = "doctor")
	private List<Record> records;

	@OneToMany(targetEntity = Billing.class, mappedBy = "doctor")
	private List<Billing> billings;

	public String getSpecialisation() {
		return specialisation;
	}

	public void setSpecialisation(String specialisation) {
		this.specialisation = specialisation;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}


	public List<Record> getRecords() {
		return records;
	}

	public void setRecords(List<Record> records) {
		this.records = records;
	}

}
