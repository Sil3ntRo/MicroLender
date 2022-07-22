package com.rohansideproject.microlender.domain.model;

import java.time.Duration;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public final class LoanApplication {
	
	@Id
	private long id;
	private int amount;
	@ManyToOne
	private User borrower;
	private int repaymentTermInDays;
	private double interestRate;
	
	public LoanApplication() {
		
	}
	
	public LoanApplication(int amount, User borrower, int repaymentTermInDays, double interestRate) {
		this.amount = amount;
		this.borrower = borrower;
		this.repaymentTermInDays = repaymentTermInDays;
		this.interestRate = interestRate;
	}

	public int getAmount() {
		return amount;
	}

	public User getBorrower() {
		return borrower;
	}

	public int getRepaymentTermInDays() {
		return repaymentTermInDays;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, borrower, interestRate, repaymentTermInDays);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoanApplication other = (LoanApplication) obj;
		return amount == other.amount && Objects.equals(borrower, other.borrower)
				&& Double.doubleToLongBits(interestRate) == Double.doubleToLongBits(other.interestRate)
				&& Objects.equals(repaymentTermInDays, other.repaymentTermInDays);
	}

	@Override
	public String toString() {
		return "LoanRequest [amount=" + amount + ", borrower=" + borrower + ", repaymentTerm=" + repaymentTermInDays
				+ ", interestRate=" + interestRate + "]";
	}
	
	
}
