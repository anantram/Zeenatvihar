package org.anantram.zeenat.domain.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
@Entity
public class AccountDueRelationship {
	@Id
	@GeneratedValue
	private Long Id;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Account account;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Due due;
	
	private boolean paid;
	
	private Date paidOn;
	
}
