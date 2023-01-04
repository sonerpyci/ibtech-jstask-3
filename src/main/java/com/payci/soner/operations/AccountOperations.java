package com.payci.soner.operations;

import com.payci.soner.entities.Account;
import com.payci.soner.helpers.data.carrier.Bag;
import com.payci.soner.hibernate.AccountRepository;

public class AccountOperations {
	public Bag createAccountCommand(Bag inBag) {
		Bag outBag = new Bag();
		AccountRepository accountRepository = new AccountRepository();
		Double balance = Double.parseDouble(inBag.get("ACCOUNT_BALANCE").toString());
		Account account = new Account(balance);
		accountRepository.saveOrUpdate(account);
		outBag.put("account", account);
		return outBag;
	}
}
