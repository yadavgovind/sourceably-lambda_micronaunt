//package com.oms.projectbuddy.config;
//
//
//import jakarta.inject.Singleton;
//
//import java.io.Serializable;
//import java.util.*;
//import java.util.function.Function;
//import java.util.stream.Collectors;
//
//import static com.oms.projectbuddy.utils.Constants.*;
//
//
//@Singleton
//public class TokenProvider implements Serializable {
//
//    /**
//	 *
//	 */
//	private static final long serialVersionUID = -2229623044549449938L;
//
//	public String getUsernameFromToken(String token) {
//        return getClaimFromToken(token, Claims::getSubject);
//    }
//
//    public Date getExpirationDateFromToken(String token) {
//        return getClaimFromToken(token, Claims::getExpiration);
//    }
//
//    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = getAllClaimsFromToken(token);
//        return claimsResolver.apply(claims);
//    }
//
//    public Claims getAllClaimsFromToken(String token) {
//        return Jwts.parser()
//                .setSigningKey(SIGNING_KEY)
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    public Boolean isTokenExpired(String token) {
//        final Date expiration = getExpirationDateFromToken(token);
//        return expiration.before(new Date());
//    }
//
//    public String generateToken(Authentication authentication, String userType,String userId) {
//        final String authorities = authentication.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.joining(","));
//        //todo need to add once get time
////        Map<String , Object> map= new HashMap<>();
////        map.put("userType",userType);
////        map.put("userId",userId);
//        return Jwts.builder()
//                .setSubject(authentication.getName())
//              //  .setClaims(map)
//                .claim(AUTHORITIES_KEY, authorities)
//                .signWith(SignatureAlgorithm.HS512, SIGNING_KEY)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS*1000))
//                .compact();
//    }
//
//    public Boolean validateToken(String token, UserDetails userDetails) {
//        final String username = getUsernameFromToken(token);
//        return (
//              username.equals(userDetails.getUsername())
//                    && !isTokenExpired(token));
//    }
//
//    UsernamePasswordAuthenticationToken getAuthentication(final String token, final Authentication existingAuth, final UserDetails userDetails) {
//
//        final JwtParser jwtParser = Jwts.parser().setSigningKey(SIGNING_KEY);
//
//        final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);
//
//        final Claims claims = claimsJws.getBody();
//
//        final Collection<? extends GrantedAuthority> authorities =
//                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
//                        .map(SimpleGrantedAuthority::new)
//                        .collect(Collectors.toList());
//
//        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
//    }
//
//}
