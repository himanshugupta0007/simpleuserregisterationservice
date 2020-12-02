/**
 * 
 */
package com.userregisteration.listener;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.userregisteration.event.OnRegistrationCompleteEvent;
import com.userregisteration.modal.User;
import com.userregisteration.service.IUserService;
import com.userregisteration.utility.UserRegisterationUtility;

/**
 * @author himanshugupta
 *
 */
@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

	@Autowired
	private IUserService service;

	@Autowired
	private MessageSource messages;

	@Autowired
	private UserRegisterationUtility userUtility;

	/**
	 * @param event
	 */
	@Override
	public void onApplicationEvent(OnRegistrationCompleteEvent event) {

		this.confirmRegistration(event);
	}

	/**
	 * @param event
	 */
	private void confirmRegistration(OnRegistrationCompleteEvent event) {
		User user = event.getUser();
		String token = UUID.randomUUID().toString();
		service.createVerificationToken(user, token);

		String recipientAddress = user.getEmailAddress();
		String subject = "Registration Confirmation";
		String confirmationUrl = event.getAppUrl() + "/userservice/v1/regitrationConfirm?token=" + token;
		String message = messages.getMessage("message.regSucc", null, event.getLocale());
		String userMailMessage = message + "\r\n" + "http://localhost:8080" + confirmationUrl;
		this.userUtility.sendmail(recipientAddress, subject, userMailMessage);

	}
}
