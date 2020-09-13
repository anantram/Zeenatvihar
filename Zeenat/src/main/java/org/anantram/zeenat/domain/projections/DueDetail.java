package org.anantram.zeenat.domain.projections;

import java.time.Month;
import java.time.Year;

import org.springframework.beans.factory.annotation.Value;

public interface DueDetail {
	
	@Value("#{target.account.flatNumber}")
	public String getFlatNumber();
	
	@Value("#{target.due.dueMonth}")
	public Month getDueMonth();
	
	@Value("#{target.due.dueYear}")
	public Year getDueYear();
	
	@Value("#{target.due.dueAmount}")
	public double getDueAmount();

}
