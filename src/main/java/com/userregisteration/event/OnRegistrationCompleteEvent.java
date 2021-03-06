/**
 * 
 */
package com.userregisteration.event;

import java.util.Locale;

import org.springframework.context.ApplicationEvent;

import com.userregisteration.modal.User;

/**
 * @author himanshugupta
 *
 */
public class OnRegistrationCompleteEvent extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String appUrl;
	private Locale locale;
	private User user;

	/**
	 * @param source
	 * @param appUrl
	 * @param locale
	 * @param user
	 */
	public OnRegistrationCompleteEvent(String appUrl, Locale locale, User user) {
		super(user);
		this.appUrl = appUrl;
		this.locale = locale;
		this.user = user;
	}

	/**
	 * @return the appUrl
	 */
	public String getAppUrl() {
		return appUrl;
	}

	/**
	 * @param appUrl the appUrl to set
	 */
	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	/**
	 * @return the locale
	 */
	public Locale getLocale() {
		return locale;
	}

	/**
	 * @param locale the locale to set
	 */
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

}
