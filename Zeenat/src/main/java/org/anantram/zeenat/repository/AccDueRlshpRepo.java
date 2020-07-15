package org.anantram.zeenat.repository;

import java.util.List;

import org.anantram.zeenat.domain.DueDetails;
import org.anantram.zeenat.domain.entities.AccountDueRelationship;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AccDueRlshpRepo extends CrudRepository<AccountDueRelationship, Long> {

	
	@Query("select new org.anantram.zeenat.domain.DueDetails(a.flatNumber, d.dueAmount, d.dueMonth, d.dueYear, adr.paidOn) "
			+ "from Account a, Due d, AccountDueRelationship adr "
			+ "where adr.account = a.id "
			+ "and adr.due = d.Id "
			+ "and adr.paidOn is NULL")
	List<DueDetails> getDueDetails();
	
	@Query("select new org.anantram.zeenat.domain.DueDetails(a.flatNumber, d.dueAmount, d.dueMonth, d.dueYear, adr.paidOn) "
			+ "from Account a, Due d, AccountDueRelationship adr "
			+ "where adr.account = a.id "
			+ "and adr.due = d.Id "
			+ "and adr.paidOn is NULL "
			+ "and a.flatNumber = :flatNumber")
	List<DueDetails> getDueDetails(@Param("flatNumber") final String flatNumber);
	
	@Query("select sum(d.dueAmount) from Account a, Due d, AccountDueRelationship adr " 
			 + "where adr.account = a.id "
			+  "and adr.due = d.Id "
			+ "and a.flatNumber = :flatNumber "
			+ "and adr.paidOn is NULL")
	double gettotalDue(@Param("flatNumber") final String flatNumber);
	
	
}
