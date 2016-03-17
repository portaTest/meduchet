package tesla.meduchet.domain;

import java.util.Date;
import java.util.List;

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

import tesla.meduchet.domain.enumeration.RecordStatus;

@Entity
@Table(name = "T_Record")
public class Record {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "date")
	@Temporal(TemporalType.DATE)
	private Date date;

	@Temporal(TemporalType.TIME)
	@Column(name = "duration")
	private Date duration;

	@Temporal(TemporalType.TIME)
	@Column(name = "startTime")
	private Date startTime;

	@Temporal(TemporalType.TIME)
	@Column(name = "endTime")
	private Date endTime;

	@OneToOne(targetEntity = Operation.class)
	private List<Operation> operation;

	@ManyToOne(targetEntity = Patient.class)
	@JoinColumn(name = "patientId")
	private Patient patient;

	@ManyToOne(targetEntity = Doctor.class)
	@JoinColumn(name = "doctorId")
	private Doctor doctor;

	@Column(name = "recordStatus")
	private RecordStatus status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public Date getDuration() {
		return duration;
	}

	public void setDuration(Date duration) {
		this.duration = duration;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Operation> getOperation() {
		return operation;
	}

	public void setOperation(List<Operation> operation) {
		this.operation = operation;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
}
