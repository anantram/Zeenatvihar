package org.anantram.zeenat.services;


import org.anantram.zeenat.domain.entities.Account;
import org.anantram.zeenat.repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

	
	private AccountRepo ar;

	
	@Autowired
	public AccountService(final AccountRepo accRepo)
	{
		ar = accRepo;
	}
	
	public String createAccount(String flatNumber) {
		Account a = new Account(flatNumber);
		ar.save(a);
		return "Account Created";
	}
	
	public Iterable<Account> getAccounts()
	{
		return ar.findAll();
	}


}
