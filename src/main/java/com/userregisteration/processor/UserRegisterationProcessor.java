/**
 * 
 */
package com.userregisteration.processor;

import java.util.Calendar;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.userregisteration.dto.UserDTO;
import com.userregisteration.dto.UserRegisterationRequestDTO;
import com.userregisteration.exception.UserAlreadyExistException;
import com.userregisteration.modal.User;
import com.userregisteration.modal.VerificationToken;
import com.userregisteration.service.IUserService;
import com.userregisteration.utility.DTOToModelMapper;

/**
 * @author himanshugupta
 *
 */
@Component
public class UserRegisterationProcessor {

	IUserService userService;

	PasswordEncoder passEncoder;

	/**
	 * @param userService
	 */
	@Autowired
	public UserRegisterationProcessor(final IUserService userService, final PasswordEncoder passwordEncoder) {
		this.userService = userService;
		this.passEncoder = passwordEncoder;
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

}
