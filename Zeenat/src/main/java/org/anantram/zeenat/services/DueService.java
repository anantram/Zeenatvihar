package org.anantram.zeenat.services;


import java.util.Iterator;
import java.util.List;

import org.anantram.zeenat.domain.DueDetails;
import org.anantram.zeenat.domain.entities.Account;
import org.anantram.zeenat.domain.entities.AccountDueRelationship;
import org.anantram.zeenat.domain.entities.Due;
import org.anantram.zeenat.repository.AccDueRlshpRepo;
import org.anantram.zeenat.repository.AccountRepo;
import org.anantram.zeenat.repository.DueRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DueService {
	
	
	private AccountRepo ar;
	private AccDueRlshpRepo adr;
	 @Autowired
		public DueService(final DueRepo dueRepo, final AccDueRlshpRepo adr, final AccountRepo ar) {
		 this.adr = adr;
		 this.ar = ar;
		}
	
	public void triggerDue(Due due)
	{
		Iterator<Account> abcd = ar.findAll().iterator();
		while (abcd.hasNext())
		{
			AccountDueRelationship newAdr = new AccountDueRelationship();
			newAdr.setDue(due);
			newAdr.setAccount(abcd.next());
			newAdr.setPaid(false);
			adr.save(newAdr);
		}
		
	}
	
	/**
	 * get list of all pending dues;
	 * @return
	 */
	public List<DueDetails> getDues()
	{
		return adr.getDueDetails();
	}
	
	/**
	 * get list of all pending dues;
	 * @return
	 */
	public List<DueDetails> getDues(String flatNumber)
	{
		return adr.getDueDetails(flatNumber);
	}
	
	public double getTotalDue(String flatNumber)
	{
		return adr.gettotalDue(flatNumber);
	}

}
