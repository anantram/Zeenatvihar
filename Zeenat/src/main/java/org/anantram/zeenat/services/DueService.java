package org.anantram.zeenat.services;


import java.time.Month;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.anantram.zeenat.domain.DueDetails;
import org.anantram.zeenat.domain.entities.Account;
import org.anantram.zeenat.domain.entities.AccountDueRelationship;
import org.anantram.zeenat.domain.entities.Due;
import org.anantram.zeenat.repository.AccDueRlshpRepo;
import org.anantram.zeenat.repository.AccountRepo;
import org.anantram.zeenat.repository.DueRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public class DueService {
	
	
	private AccountRepo ar;
	private AccDueRlshpRepo adr;
	private DueRepo dr;
	 @Autowired
	public DueService(final DueRepo dueRepo, final AccDueRlshpRepo adr, final AccountRepo ar, final DueRepo dr) {
	 this.adr = adr;
	 this.ar = ar;
	 this.dr = dr;
	}
	
	 /**
	  * Trigger new due for all enrolled accounts
	  * @param due
	  */
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
	
	/**
	 * Total amount due for a flat
	 * @param flatNumber
	 * @return
	 */
	public double getTotalDue(String flatNumber)
	{
		return adr.gettotalDue(flatNumber);
	}
	
	/**
	 * Paying the due
	 * @param flatNumber
	 * @param dueMonth
	 * @param dueYear
	 * @return
	 */
	public String payDue(final String flatNumber,final String dueMonth,int dueYear)
	{
		Long aa = adr.getaccountDueId(flatNumber, 
				Month.valueOf(dueMonth.toUpperCase()),
				Year.of(dueYear));
		
		if (aa == null) {
			return "There is no record for  Flat number: "						
				+ flatNumber
				+ ", Due Month: "		
				+ dueMonth
				+ ", Due Year: "
				+ dueYear;
		}
		AccountDueRelationship abcd = adr.findById(aa).orElseThrow(null);
			abcd.setPaidOn(new Date());
			adr.save(abcd);
			
			return "Successfully Paid";
	}
	
	public String updateDue(final String dueMonth,int dueYear, double amount)
	{
		List<Due> ss = dr.findFirst1ByDueMonthAndDueYear(Month.valueOf(dueMonth.toUpperCase()), Year.of((dueYear)));
		Due a = ss.get(0);
		a.setDueAmount(amount);
		dr.save(a);
		return "Due of " + dueMonth + " " + dueYear + " has been sucessfully updated to: " + amount;  
	}
	

}
