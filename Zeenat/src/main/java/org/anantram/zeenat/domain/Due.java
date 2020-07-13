package org.anantram.zeenat.domain;
import java.time.Month;

import javax.persistence.*;

import lombok.*;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
public class Due{
	@Id
	@GeneratedValue
	private Long Id;
	private Month dueMonth;
	private double dueAmount;
	private double fine;
	private boolean paid;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Account accountId;
	
}

