package com.oms.projectbuddy;//package com.oms.projectbuddy;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
//
//@SpringBootApplication
//public class ProjectbuddyApplication extends SpringBootServletInitializer {
//
//
//	public static void main(String[] args) {
//		SpringApplication.run(ProjectbuddyApplication.class, args);
//	}
///*
//	@JsonFormat(pattern = "MM-dd-yyyy")
//	@JsonSerialize(using = LocalDateSerializer.class)
//	@JsonDeserialize(using = LocalDateDeserializer.class)
//	public LocalDate convertFormat(LocalDate localDate){
//		return localDate;
//	}
//
//	@Bean
//	public Formatter<LocalDate> localDateFormatter() {
//		return new Formatter<LocalDate>() {
//			@Override
//			public LocalDate parse(String text, Locale locale) throws ParseException {
//				return LocalDate.parse(text, DateTimeFormatter.ISO_DATE);
//			}
//
//			@Override
//			public String print(LocalDate object, Locale locale) {
//				return DateTimeFormatter.ISO_DATE.format(object);
//			}
//		};
//	}*/
//}
