package com.payci.soner.operations;

import java.util.List;
import java.util.Random;

import com.payci.soner.entities.Account;
import com.payci.soner.entities.Address;
import com.payci.soner.entities.Customer;
import com.payci.soner.entities.Phone;
import com.payci.soner.hibernate.CustomerRepository;

public class CustomerOperations {

	public void createStandaloneCustomer() {
		Customer customer = new Customer("John", "Doe");
		
		CustomerRepository customerRepository = new CustomerRepository();
		customerRepository.saveOrUpdate(customer);
	}
	
	public void createFullCustomer() {
		CustomerRepository customerRepository = new CustomerRepository();

		Customer customer = new Customer("Full", "Customer");
		customerRepository.saveOrUpdate(customer);

		Address address1 = new Address("Manisa", "Salihli", "45300", "bulunamayan adres.");
		Address address2 = new Address("Gebze", "Merkez", "????", "ibtech adres.");
		customer.addAddres(address1);
		customer.addAddres(address2);
		
		Account account = new Account(0.0);
		customer.addAccount(account);
		
		Phone phone = new Phone("+90", "555 222 11 00");
		customer.addPhone(phone);

		customerRepository.saveOrUpdate(customer);
	}
	
	public Customer getRandomCustomer() {
		CustomerRepository customerRepository = new CustomerRepository();
		List<Customer> customers = customerRepository.getAll();
		Random rand = new Random();
		return customers.get(rand.nextInt(customers.size()));
	}
}
