package org.anantram.zeenat.domain.objects;

import java.util.Date;
import java.time.Month;
import java.time.Year;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class DueDetails {

	private String flatNumber;
	private double dueAmount;
	private Year dueYear;
	private Month dueMonth;
	private Date paidOn; 
	
	public DueDetails(String flatNumber, double dueAmount, Month dueMonth, Year dueYear, Date paidOn)
	{
		this.flatNumber = flatNumber;
		this.dueAmount = dueAmount;
		this.dueMonth = dueMonth;
		this.paidOn = paidOn;
		this.dueYear = dueYear;
	}
}
