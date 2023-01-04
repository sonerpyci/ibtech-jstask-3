package com.payci.soner.hibernate;

import java.util.List;

import javax.transaction.Transactional;

import com.payci.soner.entities.Phone;
import com.payci.soner.hibernate.base.BaseRepository;

public class PhoneRepository extends BaseRepository<Phone> {

	@Transactional
	public List<Phone> getAll() {
		return getAll(Phone.class);
	}

	@Transactional
	public Phone get(long id) {
		Phone entity = get(Phone.class, id);
		entity.getCustomer();
		return entity;
	}

	public void deleteById(long id) {
		super.deleteById(Phone.class, id);
	}
}
