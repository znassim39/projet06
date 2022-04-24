package com.salimahafirassou.paymybuddy.domain;

import java.util.Date;

import static javax.persistence.GenerationType.AUTO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(
	name="transaction"
)
public class Transaction {

	@Id
    @GeneratedValue(
            strategy = AUTO
    )
	@Column(name="id")
	private Long id;

	@ManyToOne(
            fetch = FetchType.EAGER,
            optional = false
    )
	@JoinColumn(
            name = "idCredeted"
    )
	private UserEntity credeted;
	
	@ManyToOne(
            fetch = FetchType.EAGER,
            optional = true
    )
	@JoinColumn(
            name = "idDebited"
    )
	private UserEntity debited;
	
	@Column(name="payment_date")
	private Date paymentDate;

	@Column(name="amount")
	private Double amount;

	@Column(name="description")
	private String description;

	public Transaction() {};
	
	public Transaction(Long id, UserEntity credeted, UserEntity debited, Date paymentDate, Double amount, String description) {
		this.id = id;
		this.credeted = credeted;
		this.debited = debited;
		this.paymentDate = paymentDate;
		this.amount = amount;
		this.description = description;
	}
	
	public Transaction(UserEntity credeted, UserEntity debited, Date paymentDate, Double amount, String description) {
		this.credeted = credeted;
		this.debited = debited;
		this.paymentDate = paymentDate;
		this.amount = amount;
		this.description = description;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	
	public UserEntity getCredeted() { return credeted; }
	public void setCredeted(UserEntity credeted) { this.credeted = credeted; }
	
	public UserEntity getDebited() { return debited; }
	public void setDebited(UserEntity debited) { this.debited = debited; }
	
	public Date getPaymentDate() { return paymentDate; }
	public void setPaymentDate(Date paymentDate) { this.paymentDate = paymentDate; }

	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }
	
}
