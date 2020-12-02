/**
 * 
 */
package com.userregisteration.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.userregisteration.dto.UserDTO;
import com.userregisteration.modal.User;

/**
 * @author himanshugupta
 *
 */
public class DTOToModelMapper {

	@Autowired
	PasswordEncoder passwordEncoder;

	
	/** 
	 * @param argUser
	 * @param passEncoder
	 * @return User
	 */
	public static User convertUserDTOToModal(UserDTO argUser, PasswordEncoder passEncoder) {
		User user = new User();
		user.setEmailAddress(argUser.getEmailaddress());
		user.setFirstName(argUser.getFirstname());
		user.setLastName(argUser.getLastname());
		user.setPhoneNumber(argUser.getPhonenumber());
		user.setPassword(passEncoder.encode(argUser.getPassword()));
		return user;

	}
}
