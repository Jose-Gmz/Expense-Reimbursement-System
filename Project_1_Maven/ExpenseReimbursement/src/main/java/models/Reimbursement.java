package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reimbursements")
public class Reimbursement {
	
	@Id
	@Column(name="claim_id")
	int claimId;
	
	@Column(name="description")
	String description;
	
	@Column(name="amount")
	double amount;
	
	@Column(name="status")
	String reinbursementStatus;
	
	@ManyToOne
	private Employee employee;
	
	//Constructors
	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reimbursement(int claimId) {
		super();
		this.claimId = claimId;
	}

	public Reimbursement(int claimId, String description, double amount) {
		super();
		this.claimId = claimId;
		this.description = description;
		this.amount = amount;
	}

	

	public Reimbursement(int claimId, String description, double amount, String reinbursementStatus) {
		super();
		this.claimId = claimId;
		this.description = description;
		this.amount = amount;
		this.reinbursementStatus = reinbursementStatus;
	}
	
	public Reimbursement(int claimId, String description, double amount, String reinbursementStatus,
			Employee employee) {
		super();
		this.claimId = claimId;
		this.description = description;
		this.amount = amount;
		this.reinbursementStatus = reinbursementStatus;
		this.employee = employee;
	}
	
	
	public Reimbursement(int claimId, String reinbursementStatus) {
		super();
		this.claimId = claimId;
		this.reinbursementStatus = reinbursementStatus;
	}
	
	
	

	//getters and setters

	public Reimbursement(String description, double amount, String reinbursementStatus, Employee employee) {
		super();
		this.description = description;
		this.amount = amount;
		this.reinbursementStatus = reinbursementStatus;
		this.employee = employee;
	}

	public Reimbursement(String reinbursementStatus, Employee employee) {
		super();
		this.reinbursementStatus = reinbursementStatus;
		this.employee = employee;
	}

	public int getClaimId() {
		return claimId;
	}

	public void setClaimId(int claimId) {
		this.claimId = claimId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReinbursementStatus() {
		return reinbursementStatus;
	}

	public void setReinbursementStatus(String reinbursementStatus) {
		this.reinbursementStatus = reinbursementStatus;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	
	
}
