package com.oms.projectbuddy.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import jakarta.inject.Singleton;

@Singleton
public class DateTimeUtil {

	public LocalDate getTodayDate() {
		return Instant.ofEpochMilli(Instant.now().toEpochMilli()).atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
	public static LocalDate stringToLocalDate(String date){
		return LocalDate.parse(date);
	}
	
}
