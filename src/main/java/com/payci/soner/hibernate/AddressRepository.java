package com.payci.soner.hibernate;

import java.util.List;

import javax.transaction.Transactional;

import com.payci.soner.entities.Address;
import com.payci.soner.hibernate.base.BaseRepository;

public class AddressRepository extends BaseRepository<Address> {

	@Transactional
	public List<Address> getAll() {
		return getAll(Address.class);
	}

	@Transactional
	public Address get(long id) {
		Address entity = get(Address.class, id);
		entity.getCustomer();
		return entity;
	}

	public void deleteById(long id) {
		super.deleteById(Address.class, id);
	}
}