package org.anantram.zeenat.repository;

import java.time.Month;
import java.time.Year;
import java.util.List;

import org.anantram.zeenat.domain.entities.AccountDueRelationship;
import org.anantram.zeenat.domain.projections.DueDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AccDueRlshpRepo extends CrudRepository<AccountDueRelationship, Long> {

	@Query("select sum(d.dueAmount) from Account a, Due d, AccountDueRelationship adr " 
			 + "where adr.account = a.id "
			+  "and adr.due = d.Id "
			+ "and a.flatNumber = :flatNumber "
			+ "and adr.paidOn is NULL")
	double gettotalDue(@Param("flatNumber") final String flatNumber);

	
	/**
	 * List of total dues for each flat
	 * @return
	 */
	List<DueDetail> findByPaidOnIsNull();
	
	/**
	 * List of all pending dues for a flat
	 * @param flatNumber
	 * @return
	 */
	List<DueDetail> findByAccountFlatNumberAndPaidOnIsNull(String flatNumber);
	
	/**
	 * Get the due which has to be paid
	 * @param flatNumber
	 * @param dueMonth
	 * @param dueYear
	 * @return
	 */
	AccountDueRelationship findByAccountFlatNumberAndDueDueMonthAndDueDueYear(String flatNumber, Month dueMonth, Year dueYear);
	
	
}
