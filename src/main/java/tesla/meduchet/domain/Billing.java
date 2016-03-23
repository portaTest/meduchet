package tesla.meduchet.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import tesla.meduchet.domain.enumeration.PaymentType;

@Entity
@Table(name = "T_Billing")
public class Billing {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "cost")
	private BigDecimal cost;

	@ManyToOne(targetEntity = Patient.class)
	@JoinColumn(name = "patientId")
	private Patient patient;

	@OneToOne(targetEntity = Record.class)
	private Record record;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date")
	private Date date;

	@Column(name = "paymentType")
	private PaymentType paymentType;

	@ManyToOne(targetEntity = Doctor.class)
	@JoinColumn(name = "doctorId")
	private Doctor doctor;

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Record getRecord() {
		return record;
	}

	public void setRecord(Record record) {
		this.record = record;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
}
