package com.rohansideproject.microlender.domain.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public final class Money {
	
	public static final Money ZERO = new Money(0, Currency.USD);
	
	@Id
	@GeneratedValue
	private long id;
	private Currency currency;
	private BigDecimal amount;
	
	public Money() {
		
	}
	
	public Money(double amount, Currency currency) {
		this.currency = currency;
		this.amount = BigDecimal.valueOf(amount).setScale(2, RoundingMode.HALF_DOWN);
	}
	
	public Money times(double multiplier) {
		return new Money(amount.doubleValue() * multiplier, currency);
	}
	
	public Money add(final Money money) {
		if(currency != money.getCurrency()) {
			throw new IllegalArgumentException();
			
		}
		return new Money(amount.doubleValue() + money.getAmount(), currency);
	}
	
	public Money minus(final Money money) {
		if(currency != money.getCurrency() || amount.doubleValue() < money.getAmount()) {
			throw new IllegalArgumentException();
		}
		
		return new Money(amount.doubleValue() - money.getAmount(), currency);
	}

	public Currency getCurrency() {
		return currency;
	}

	public double getAmount() {
		return amount.doubleValue();
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, currency, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Money other = (Money) obj;
		return Objects.equals(amount, other.amount) && currency == other.currency && id == other.id;
	}

	@Override
	public String toString() {
		return "Money [currency=" + currency + ", amount=" + amount + "]";
	}
	
	
}
