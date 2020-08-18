package org.anantram.zeenat.domain.entities;
import java.time.Month;
import java.time.Year;

import javax.persistence.*;

import org.anantram.zeenat.converters.YearAttributeConverter;

import lombok.*;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table (uniqueConstraints= @UniqueConstraint(columnNames={"dueMonth", "dueYear"}))
public class Due{
	@Id
	@GeneratedValue
	private Long Id;
	private Month dueMonth;
	
	@Convert(converter = YearAttributeConverter.class)
	private Year dueYear;
	private double dueAmount;
	private double fine;
	
}

