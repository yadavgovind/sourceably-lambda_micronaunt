package com.oms.projectbuddy.utils;/*
 * package com.oms.projectbuddy.utils;
 * 
 * import javax.persistence.AttributeConverter; import
 * javax.persistence.Converter;
 * 
 * @Converter(autoApply = true) public class StatusEnumConverter implements
 * AttributeConverter<StatusEnum, String> {
 * 
 * @Override public String convertToDatabaseColumn(StatusEnum attribute) {
 * if(attribute==null){ return ""; } return attribute.getCustomerValue(); }
 * 
 * @Override public StatusEnum convertToEntityAttribute(String dbData) { return
 * StatusEnum.valueOf(dbData); }
 * 
 * }
 */