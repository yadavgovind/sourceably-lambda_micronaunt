package com.oms.projectbuddy.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CognitoIdentityFilter  {
	 public static final String COGNITO_IDENTITY_ATTRIBUTE ="com.amazonaws.serverless.cognitoId";
	 
     private static Logger log = LoggerFactory.getLogger(CognitoIdentityFilter.class);
 
   /*  @Override
     public void init(FilterConfig filterConfig) throws ServletException {}
     @Override
     public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
             throws IOException, ServletException {
         Object apiGwContext = request.getAttribute(RequestReader.API_GATEWAY_CONTEXT_PROPERTY);
         if (apiGwContext == null) {
             log.warn("API Gateway context is null");
             chain.doFilter(request,response);
         }

         chain.doFilter(request,response);
     }
     @Override
     public void destroy() {}*/
}
