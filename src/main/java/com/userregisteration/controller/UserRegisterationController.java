/**
 * 
 */
package com.userregisteration.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.userregisteration.dto.PasswordDto;
import com.userregisteration.dto.UserRegisterationRequestDTO;
import com.userregisteration.event.OnRegistrationCompleteEvent;
import com.userregisteration.modal.User;
import com.userregisteration.processor.UserRegisterationProcessor;
import com.userregisteration.responseenvelope.RegisterResponseEnvelope;

/**
 * @author himanshugupta Controller class to expose endpoint to register users
 */

@RestController
@RequestMapping("userservice/v1")
public class UserRegisterationController {

	UserRegisterationProcessor userProcessor;

	ApplicationEventPublisher eventPublisher;

	MessageSource messages;

	/**
	 * @param userProcessor
	 */
	@Autowired
	public UserRegisterationController(final UserRegisterationProcessor userProcessor,
			final ApplicationEventPublisher eventPublisher, final MessageSource messages) {
		this.userProcessor = userProcessor;
		this.eventPublisher = eventPublisher;
		this.messages = messages;
	}

	/**
	 * @param argUserDTO
	 * @param request
	 * @return RegisterResponseEnvelope
	 */
	@PostMapping(value = "/registerUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public RegisterResponseEnvelope registerUser(@RequestBody @Valid UserRegisterationRequestDTO argUserDTO,
			HttpServletRequest request) {
		User registeredUser = this.userProcessor.createUser(argUserDTO);
		String appURL = request.getContextPath();
		eventPublisher.publishEvent(new OnRegistrationCompleteEvent(appURL, request.getLocale(), registeredUser));
		return new RegisterResponseEnvelope("Success: " + registeredUser.getId(), "");
	}

	/**
	 * @param request
	 * @param token
	 * @return String
	 */
	@GetMapping("/regitrationConfirm")
	public String confirmRegisteration(WebRequest request, @RequestParam("token") String token) {

		Locale locale = request.getLocale();
		if (token == null) {
			return "Token is null";
		}
		if (userProcessor.confirmRegisteration(locale, token)) {
			return "User is Enabled";
		} else {
			return "Invalid Token";
		}
	}

	/**
	 * @param userPhoneNumber
	 * @return RegisterResponseEnvelope
	 */
	@PostMapping("/resetPassword")
	public RegisterResponseEnvelope resetPassword(@RequestParam("phoneNumber") String userPhoneNumber,
			HttpServletRequest request) {
		this.userProcessor.resetPassword(userPhoneNumber, request);
		return new RegisterResponseEnvelope(
				messages.getMessage("message.resetPasswordEmail", null, Locale.getDefault()), "");
	}

	/**
	 * @param token
	 * @return RegisterResponseEnvelope
	 */
	@GetMapping("/validatePasswordToken")
	public RegisterResponseEnvelope validatePasswordToken(@RequestParam("token") String token) {
		String tokenValidation = this.userProcessor.validatePasswordToken(token);
		return new RegisterResponseEnvelope("Your Token is", tokenValidation);
	}

	@PostMapping("/savePassword")
	public RegisterResponseEnvelope saveResetPassword(@Valid @RequestBody PasswordDto passwordDto){
		this.userProcessor.saveResetPassword(passwordDto);
		return new RegisterResponseEnvelope("Password Reset Successfull", "");
	}
}
