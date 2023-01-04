package com.payci.soner.operations;

import java.util.List;
import java.util.Random;

import com.payci.soner.CommandExecuter;
import com.payci.soner.entities.Account;
import com.payci.soner.entities.Address;
import com.payci.soner.entities.Customer;
import com.payci.soner.entities.Phone;
import com.payci.soner.helpers.data.carrier.Bag;
import com.payci.soner.hibernate.CustomerRepository;

public class CustomerOperations {

	public Bag createStandaloneCustomer(Bag inBag) {
		Bag outBag = new Bag();
		
		String name = (String)inBag.get("CUSTOMER_NAME");
		String lastName = (String)inBag.get("CUSTOMER_LAST_NAME");
		
		Customer customer = new Customer(name, lastName);
		
		CustomerRepository customerRepository = new CustomerRepository();
		customerRepository.saveOrUpdate(customer);
		
		return outBag;
	}
	
	public Bag createFullCustomer(Bag inBag) throws Exception {
		Bag outBag = new Bag();
		
		CustomerRepository customerRepository = new CustomerRepository();

		String name = (String)inBag.get("CUSTOMER_NAME");
		String lastName = (String)inBag.get("CUSTOMER_LAST_NAME");
		
		Customer customer = new Customer(name, lastName);

		CommandExecuter commandExecuter = new CommandExecuter();
		
		Bag resultBag = commandExecuter.Execute("AddressOperations_createAddressCommand");
		customer.addAddres((Address)resultBag.get("address"));
		
		resultBag = commandExecuter.Execute("PhoneOperations_createPhoneCommand");
		customer.addPhone((Phone)resultBag.get("phone"));
		
		resultBag = commandExecuter.Execute("AccountOperations_createAccountCommand");
		customer.addAccount((Account)resultBag.get("account"));

		customerRepository.saveOrUpdate(customer);
		outBag.put("customer", customer);
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
