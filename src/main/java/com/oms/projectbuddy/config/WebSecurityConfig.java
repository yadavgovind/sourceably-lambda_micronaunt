//package com.oms.projectbuddy.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
//
//import javax.annotation.Resource;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Resource(name = "companyRegistrationService")
//    private UserDetailsService userDetailsService;
//
//
//    @Autowired
//    private JwtAuthenticationEntryPoint unauthorizedHandler;
//
//    @Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Autowired
//    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService)
//                .passwordEncoder(encoder());
//    }
//
//    @Bean
//    public com.oms.projectbuddy.config.JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception {
//        return new com.oms.projectbuddy.config.JwtAuthenticationFilter();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//           http.cors().and().csrf().disable().
//                authorizeRequests()
//                .antMatchers("/api/auth/**","/swagger-ui/**", "/swagger-ui.html", "/swagger-resources/**"
//                        , "/v3/api-docs/**", "/webjars/**",
//                        "/api/companyLogin",
//                        "/api/forgotPassword",
//                        "/api/registration",
//                        "/api/getAllProjects",
//                        "/api/getParentCategories",
//                        "/api/m/**",
//                        "/api/searchProjects",
//                        "/api/getChildSkillCategoryBySkillId",
//                        "/api/companyLogout"
//                		).permitAll()
//                .anyRequest().authenticated().and().
//                exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and().sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
//    }
//    @Bean
//        public BasicAuthenticationEntryPoint swaggerAuthenticationEntryPoint() {
//           BasicAuthenticationEntryPoint entryPoint = new BasicAuthenticationEntryPoint();
//           entryPoint.setRealmName("Swagger Realm");
//           return entryPoint;
//       }
//
//
//
//    @Bean
//    public BCryptPasswordEncoder encoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//}
