//package com.oms.projectbuddy.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Component
//@Configuration
//public class CORSFilter {
//
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedOrigins("*")
//                        .maxAge(3600)
//                        .allowCredentials(false)
//                        .allowedOriginPatterns("*")
//                        .exposedHeaders("*")
//                        .allowedOriginPatterns("*").allowedHeaders("*")
//                        .allowedMethods("POST", "GET", "DELETE", "PUT");
//            }
//        };
//    }
//
//}
