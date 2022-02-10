//package com.oms.projectbuddy.config;
//
//import io.jsonwebtoken.Claims;
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//
//import java.util.Optional;
//import java.util.function.Function;
//
//@Component
//@AllArgsConstructor
//public class TokenContext {
//    @Inject
//    private TokenProvider tokenParser;
//
//    public String token() {
//        return (String) Optional.ofNullable(SecurityContextHolder.getContext()).map(SecurityContext::getAuthentication)
//                .map(Authentication::getCredentials).orElse(null);
//    }
//
//    public String userId() {
//        Claims claims = tokenParser.getAllClaimsFromToken(token());
//        return String.valueOf(claims.get("userId"));
//    }
//
//    public String userType() {
//        Claims claims = tokenParser.getAllClaimsFromToken(token());
//        return String.valueOf(claims.get("userType"));
//    }
//
//    private String extract(Function<String, String> extractor) {
//        return extractor.apply(token());
//    }
//}