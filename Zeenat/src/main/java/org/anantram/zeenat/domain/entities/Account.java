package org.anantram.zeenat.domain.entities;

import javax.persistence.*;

import lombok.*;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
public class Account {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String flatNumber;
	
	public Account(String flatNumber)
	{
		this.flatNumber = flatNumber;
	}


}
