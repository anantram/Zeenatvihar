package org.anantram.zeenat.domain.convertors;

import java.time.Year;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

//https://vladmihalcea.com/java-time-year-month-jpa-hibernate/

@Converter(autoApply = true)
public class YearAttributeConverter
        implements AttributeConverter<Year, Short> {
 
    @Override
    public Short convertToDatabaseColumn(
            Year attribute) {
        return (short) attribute.getValue();
    }
 
    @Override
    public Year convertToEntityAttribute(
            Short dbData) {
        return Year.of(dbData);
    }
}
