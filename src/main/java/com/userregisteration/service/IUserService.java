/**
 * 
 */
package com.userregisteration.service;

import com.userregisteration.modal.User;
import com.userregisteration.modal.VerificationToken;

/**
 * @author himanshugupta
 *
 */
public interface IUserService {

	public User registeruser(User user);

	public boolean userExists(String email);
	
	void createVerificationToken(User user, String token);
	 
    VerificationToken getVerificationToken(String VerificationToken);
    
	User getUser(String verificationToken);
	
	User findUserByPhoneNumber(String argPhoneNumber);

	public void createPasswordResetToken(User user, String token);

	public String validatePasswordToken(String token);

}
