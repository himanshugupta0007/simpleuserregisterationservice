/**
 * 
 */
package com.userregisteration.handler;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;

/**
 * @author himanshugupta
 *	Handler for Authentication Failure
 */

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Autowired
	MessageSource messagSource;
	
	@Autowired
    private LocaleResolver localeResolver;
	
	
	/** 
	 * @param request
	 * @param response
	 * @param exception
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		super.onAuthenticationFailure(request, response, exception);
		
		Locale locale = localeResolver.resolveLocale(request);
		 
        String errorMessage = messagSource.getMessage("message.badCredentials", null, locale);
 
        if (exception.getMessage().equalsIgnoreCase("User is disabled")) {
            errorMessage = messagSource.getMessage("auth.message.disabled", null, locale);
        } else if (exception.getMessage().equalsIgnoreCase("User account has expired")) {
            errorMessage = messagSource.getMessage("auth.message.expired", null, locale);
        }
        
        System.out.println(errorMessage);
	}
}
