package com.payci.soner.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.payci.soner.entities.base.BaseEntity;

@Entity
@Table(name = "Phones")
public class Phone extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public Phone() {}
	
	public Phone(String countryCode, String number) {
		this.countryCode = countryCode;
		this.number = number;
	}
	
	public Phone(long id, String countryCode, String number) {
		this(countryCode, number);
		this.id = id;
	}
	
	public Phone(long id, String countryCode, String number, Customer customer) {
		this(id, countryCode, number);
		this.customer = customer;
	}
	
	@Column(name = "country_code")
	private String countryCode;

	private String number;

	@ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
    public String toString() {
		StringBuilder sb = new StringBuilder();

        return sb.append("Phone : \n Id : ").append(id)
        		.append("\nCountryCode : ").append(countryCode)
        		.append("\nNumber : ").append(number)
        		.toString();
    }
}
