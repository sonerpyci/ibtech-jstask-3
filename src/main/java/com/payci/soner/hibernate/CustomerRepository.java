package com.payci.soner.hibernate;


import java.util.List;

import javax.transaction.Transactional;

import com.payci.soner.entities.Customer;
import com.payci.soner.hibernate.base.BaseRepository;

public class CustomerRepository extends BaseRepository<Customer> {

	@Transactional
	public List<Customer> getAll() {
		return getAll(Customer.class);
	}

	@Transactional
	public Customer get(long id) {
		Customer customer = get(Customer.class, id);
		
		customer.getAccounts();
		customer.getAddresses();
		customer.getPhones();
		
		return customer;
	}

	public void deleteById(long id) {
		super.deleteById(Customer.class, id);
	}

}
