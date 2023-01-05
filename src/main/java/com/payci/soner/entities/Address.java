package com.payci.soner.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.payci.soner.entities.base.BaseEntity;

@Entity
@Table(name = "Addresses")
public class Address extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public Address() {}
	
	public Address(String city, String district, String postalCode, String addressText) {
		this.city = city;
		this.district = district;
		this.postalCode = postalCode;
		this.addressText = addressText;
	}

	public Address(long id, String city, String district, String postalCode, String addressText) {
		this(city, district, postalCode, addressText);
		this.id = id;
	}

	public Address(long id, String city, String district,  String postalCode, String addressText, Customer customer) {
		this(id, city, district, postalCode, addressText);
		this.customer = customer;
	}

	private String city;
	private String district;

	@Column(name = "address_text")
	private String addressText;

	@Column(name = "postal_code")
	private String postalCode;

	@ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getAddressText() {
		return addressText;
	}

	public void setAddressText(String addressText) {
		this.addressText = addressText;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
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

        sb.append("Address : \n Id : ").append(id)
        .append("\n City : ").append(city)
        .append("\n District : ").append(district);
    		if (this.getCustomer() != null)
    			sb.append("\nCustomerId : ").append(customer.getId());
        		
        return sb.toString();
    }
}
