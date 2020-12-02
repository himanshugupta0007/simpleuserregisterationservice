/**
 * 
 */
package com.userregisteration.processor;

import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.userregisteration.dto.UserDTO;
import com.userregisteration.dto.UserRegisterationRequestDTO;
import com.userregisteration.exception.UserAlreadyExistException;
import com.userregisteration.exception.UserNotFoundException;
import com.userregisteration.modal.User;
import com.userregisteration.modal.VerificationToken;
import com.userregisteration.service.IUserService;
import com.userregisteration.utility.DTOToModelMapper;
import com.userregisteration.utility.UserRegisterationUtility;

/**
 * @author himanshugupta
 *
 */
@Component
public class UserRegisterationProcessor {

	IUserService userService;

	PasswordEncoder passEncoder;

	UserRegisterationUtility userUtility;

	@Autowired
	MessageSource message;

	/**
	 * @param userService
	 */
	@Autowired
	public UserRegisterationProcessor(final IUserService userService, final PasswordEncoder passwordEncoder,
			UserRegisterationUtility userUtility) {
		this.userService = userService;
		this.passEncoder = passwordEncoder;
		this.userUtility = userUtility;
	}

	/**
	 * @param argUserDTO
	 * @return User
	 */
	public User createUser(UserRegisterationRequestDTO argUserDTO) {
		UserDTO userDTO = argUserDTO.getUserDTO();
		if (this.userService.userExists(userDTO.getPhonenumber())) {
			throw new UserAlreadyExistException(
					"User is already registered with email address: " + userDTO.getPhonenumber());
		} else {
			User user = DTOToModelMapper.convertUserDTOToModal(userDTO, this.passEncoder);
			return this.userService.registeruser(user);
		}
	}

	/**
	 * @param locale
	 * @param token
	 * @return boolean
	 */
	public boolean confirmRegisteration(Locale locale, String token) {
		boolean isUserEnabled = true;
		VerificationToken verificationToken = userService.getVerificationToken(token);
		if (verificationToken == null) {
			return false;
		}

		User user = verificationToken.getUser();
		Calendar cal = Calendar.getInstance();
		if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
			return false;
		}

		user.setEnabled(true);
		userService.registeruser(user);
		return isUserEnabled;
	}

	/**
	 * @param userPhoneNumber
	 * @param request
	 */
	public void resetPassword(String userPhoneNumber, HttpServletRequest request) {
		User user = userService.findUserByPhoneNumber(userPhoneNumber);
		if (user == null) {
			throw new UserNotFoundException();
		}
		String token = UUID.randomUUID().toString();
		userService.createPasswordResetToken(user, token);
		this.userUtility.sendmail(user.getEmailAddress(), "Reset Password", constructMailBody(request, token));
	}

	/**
	 * @param request
	 * @param token
	 * @return String
	 */
	private String constructMailBody(HttpServletRequest request, String token) {
		String url = this.userUtility.getAppUrl(request) + "/userservice/v1/validatePasswordToken?token=" + token;
		String msg = message.getMessage("message.resetPassword", null, request.getLocale());
		return msg + " \r\n" + url;
	}

	/**
	 * @param token
	 * @return String
	 */
	public String validatePasswordToken(String token) {
		return this.userService.validatePasswordToken(token);
	}

}
