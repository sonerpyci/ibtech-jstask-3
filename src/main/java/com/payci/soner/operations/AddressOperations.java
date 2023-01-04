package com.payci.soner.operations;

import com.payci.soner.entities.Account;
import com.payci.soner.entities.Address;
import com.payci.soner.entities.Customer;
import com.payci.soner.entities.Phone;
import com.payci.soner.helpers.data.carrier.Bag;
import com.payci.soner.hibernate.AddressRepository;
import com.payci.soner.hibernate.CustomerRepository;

public class AddressOperations {
	
	public Bag createAddressCommand(Bag inBag) {
		Bag outBag = new Bag();
		
		AddressRepository addressRepository = new AddressRepository();

		String city = inBag.get("ADDR_CITY").toString();
		String district = inBag.get("ADDR_DISTRICT").toString();
		String postalCode = inBag.get("ADDR_POSTAL_CODE").toString();
		String addressText = inBag.get("ADDR_TEXT").toString();
		
		Address address = new Address(city, district, postalCode, addressText);
		
		addressRepository.saveOrUpdate(address);
		
		outBag.put("address", address);
		
		return outBag;
	}
	
	
}
