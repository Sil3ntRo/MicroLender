package com.rohansideproject.microlender.domain.model;

import java.time.Duration;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public final class LoanRequest {
	
	@Id
	private long id;
	private final int amount;
	private final User borrower;
	private final Duration repaymentTerm;
	private final double interestRate;
	
	public LoanRequest(int amount, User borrower, Duration repaymentTerm, double interestRate) {
		this.amount = amount;
		this.borrower = borrower;
		this.repaymentTerm = repaymentTerm;
		this.interestRate = interestRate;
	}

	public int getAmount() {
		return amount;
	}

	public User getBorrower() {
		return borrower;
	}

	public Duration getRepaymentTerm() {
		return repaymentTerm;
	}

	public double getInterestRate() {
		return interestRate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, borrower, interestRate, repaymentTerm);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoanRequest other = (LoanRequest) obj;
		return amount == other.amount && Objects.equals(borrower, other.borrower)
				&& Double.doubleToLongBits(interestRate) == Double.doubleToLongBits(other.interestRate)
				&& Objects.equals(repaymentTerm, other.repaymentTerm);
	}

	@Override
	public String toString() {
		return "LoanRequest [amount=" + amount + ", borrower=" + borrower + ", repaymentTerm=" + repaymentTerm
				+ ", interestRate=" + interestRate + "]";
	}
	
	
}
