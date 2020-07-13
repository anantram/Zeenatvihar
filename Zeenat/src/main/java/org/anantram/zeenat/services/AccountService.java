package org.anantram.zeenat.services;


import java.time.Month;
import java.util.Iterator;

import org.anantram.zeenat.domain.Account;
import org.anantram.zeenat.domain.Due;
import org.anantram.zeenat.repository.AccountRepo;
import org.anantram.zeenat.repository.DueRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

	
	private AccountRepo ar;
	private DueRepo dr;
	
	@Autowired
	public AccountService(final AccountRepo accRepo, final DueRepo dueRepo)
	{
		ar = accRepo;
		dr = dueRepo;
	}
	
	public String createAccount() {
		Account a = new Account();
		ar.save(a);
		return "Account Created";
	}
	
	public Iterable<Account> getAccounts()
	{
		return ar.findAll();
	}

	public void triggerDue()
	{

		Iterator<Account> abcd = ar.findAll().iterator();
		while (abcd.hasNext())
		{
			Due newDue = new Due();
			newDue.setDueMonth(Month.APRIL);
			newDue.setAccountId(abcd.next());
			dr.save(newDue);
		}
		
	}
}
