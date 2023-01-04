package com.payci.soner.operations;

import com.payci.soner.entities.Phone;
import com.payci.soner.helpers.data.carrier.Bag;
import com.payci.soner.hibernate.PhoneRepository;

public class PhoneOperations {

	public Bag createPhoneCommand(Bag inBag) {
		Bag outBag = new Bag();
		
		PhoneRepository phoneRepository = new PhoneRepository();

		String countryCode = inBag.get("PHONE_COUNTRY_CODE").toString();
		String phoneNumber = inBag.get("PHONE_NUMBER").toString();
		
		Phone phone = new Phone(countryCode, phoneNumber);
		
		phoneRepository.saveOrUpdate(phone);
		
		outBag.put("phone", phone);
		
		return outBag;
	}
	
	
}
