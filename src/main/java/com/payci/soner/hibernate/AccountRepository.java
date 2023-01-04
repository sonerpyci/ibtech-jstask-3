package com.payci.soner.hibernate;

import java.util.List;

import javax.transaction.Transactional;

import com.payci.soner.entities.Account;
import com.payci.soner.hibernate.base.BaseRepository;

public class AccountRepository extends BaseRepository<Account> {

	@Transactional
	public List<Account> getAll() {
		return getAll(Account.class);
	}

	@Transactional
	public Account get(long id) {
		Account entity = get(Account.class, id);
		entity.getCustomer();
		return entity;
	}

	public void deleteById(long id) {
		super.deleteById(Account.class, id);
	}
}
