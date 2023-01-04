package com.payci.soner.operations;

import java.util.List;
import java.util.Random;

import com.payci.soner.entities.Account;
import com.payci.soner.entities.Address;
import com.payci.soner.entities.Customer;
import com.payci.soner.entities.Phone;
import com.payci.soner.helpers.data.carrier.Bag;
import com.payci.soner.hibernate.CustomerRepository;

public class CustomerOperations {

	public Bag createStandaloneCustomer(Bag inBag) {
		Bag outBag = new Bag();
		
		// TODO : check here.
		String name = (String)inBag.get("NAME");
		String lastName = (String)inBag.get("LAST_NAME");
		
		Customer customer = new Customer(name, lastName);
		
		CustomerRepository customerRepository = new CustomerRepository();
		customerRepository.saveOrUpdate(customer);
		
		return outBag;
	}
	
	public Bag createFullCustomer(Bag inBag) {
		Bag outBag = new Bag();
		
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
		
		return outBag;
	}
	
	public Bag getCustomer(Bag inBag) {
		
		
		Bag outBag = new Bag();
		long customerId = Long.parseLong(inBag.get("CUSTOMER_ID").toString());
		
		CustomerRepository customerRepository = new CustomerRepository();
		Customer customer = customerRepository.get(customerId);
		
		outBag.put("CUSTOMER", customer);
		return outBag;
	}
	
	
	public Bag getRandomCustomer(Bag inBag) {
		Bag outBag = new Bag();
		
		CustomerRepository customerRepository = new CustomerRepository();
		List<Customer> customers = customerRepository.getAll();
		Random rand = new Random();
		Customer customer = customers.get(rand.nextInt(customers.size()));
		
		outBag.put("CUSTOMER", customer);
		return outBag;
	}
}
