package com.payci.soner.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import com.payci.soner.entities.base.BaseEntity;


@Entity
@Table(name = "Customers")
public class Customer extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	public Customer() {}
	
	public Customer(String name, String lastName) {
		this.name = name;
		this.lastName = lastName;
	}


	public Customer(long id, String name, String lastName) {
		this(name, lastName);
		this.id = id;
	}

	public Customer(long id, String name, String lastName,
			List<Account> accounts, List<Address> addresses, List<Phone> phones)
	{
		this(id, name, lastName);
		this.accounts = accounts;
		this.addresses = addresses;
		this.phones = phones;
	}

	private String name;

	@Column(name = "last_name")
	private String lastName;

	@OneToMany(mappedBy = "customer", cascade = {CascadeType.ALL})
	@Cascade(org.hibernate.annotations.CascadeType.REPLICATE)
	private List<Account> accounts = new ArrayList<>();

	@OneToMany(mappedBy = "customer", cascade = {CascadeType.ALL})
	@Cascade(org.hibernate.annotations.CascadeType.REPLICATE)
	private List<Address> addresses = new ArrayList<>();

	@OneToMany(mappedBy = "customer", cascade = {CascadeType.ALL})
	@Cascade(org.hibernate.annotations.CascadeType.REPLICATE)
	private List<Phone> phones = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Account> getAccounts() {
		return Collections.unmodifiableList(accounts);
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public void addAccount(Account account) {
		this.accounts.add(account);
		account.setCustomer(this);
	}
	
	public List<Address> getAddresses() {
		return Collections.unmodifiableList(addresses);
	}
	
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public void addAddres(Address address) {
		this.addresses.add(address);
		address.setCustomer(this);
	}

	public List<Phone> getPhones() {
		 return Collections.unmodifiableList(phones);
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public void addPhone(Phone phone) {
		this.phones.add(phone);
		phone.setCustomer(this);
	}

	
	@Override
    public String toString() {
		StringBuilder sb = new StringBuilder();

        return sb.append("Customer : \n Id : ").append(id)
        		.append("\nFirstName : ").append(name)
        		.append("\nLastName : ").append(lastName)
        		.toString();
    }
}
