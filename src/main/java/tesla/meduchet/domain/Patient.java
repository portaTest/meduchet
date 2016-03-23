package tesla.meduchet.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "T_Patient")
public class Patient extends User {

	@Column(name = "discount")
	private BigDecimal discount;

	@OneToMany(targetEntity = Billing.class, mappedBy = "patient")
	private List<Billing> billing;

	@OneToMany(targetEntity = Record.class, mappedBy = "patient")
	private List<Record> records;

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public List<Record> getRecords() {
		return records;
	}

	public void setRecords(List<Record> records) {
		this.records = records;
	}

	public List<Billing> getBilling() {
		return billing;
	}

	public void setBilling(List<Billing> billing) {
		this.billing = billing;
	}

}
