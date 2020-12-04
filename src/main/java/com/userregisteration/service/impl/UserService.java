/**
 * 
 */
package com.userregisteration.service.impl;

import java.util.Arrays;
import java.util.Calendar;

import javax.transaction.Transactional;

import com.userregisteration.modal.PasswordResetToken;
import com.userregisteration.modal.User;
import com.userregisteration.modal.VerificationToken;
import com.userregisteration.repo.PasswordResetTokenRepository;
import com.userregisteration.repo.RoleRepository;
import com.userregisteration.repo.UserRepository;
import com.userregisteration.repo.VerificationTokenRepository;
import com.userregisteration.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author himanshugupta
 *
 */
@Service
public class UserService implements IUserService {

	UserRepository userRepo;

	RoleRepository roleRepo;

	VerificationTokenRepository tokenRepo;

	PasswordResetTokenRepository passwordTokenRepository;

	/**
	 * @param userRepo
	 */
	@Autowired
	public UserService(final UserRepository userRepo, final RoleRepository roleRepo,
			final VerificationTokenRepository tokenRepo, final PasswordResetTokenRepository passwordTokenRepository) {
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
		this.tokenRepo = tokenRepo;
		this.passwordTokenRepository = passwordTokenRepository;
	}

	/**
	 * @param argUser
	 * @return User
	 */
	@Transactional
	@Override
	public User registeruser(User argUser) {
		if (argUser.getRoles() == null) {
			argUser.setRoles(Arrays.asList(roleRepo.findByName("ROLE_USER")));
		}
		return this.userRepo.saveAndFlush(argUser);

	}

	/**
	 * @param phone_number
	 * @return boolean
	 */
	@Override
	public boolean userExists(String phone_number) {
		return userRepo.findByPhoneNumber(phone_number) != null;
	}

	/**
	 * @param user
	 * @param token
	 */
	@Override
	public void createVerificationToken(User user, String token) {
		VerificationToken myToken = new VerificationToken(token, user);
		tokenRepo.save(myToken);

	}

	/**
	 * @param VerificationToken
	 * @return VerificationToken
	 */
	@Override
	public VerificationToken getVerificationToken(String VerificationToken) {
		return tokenRepo.findByToken(VerificationToken);
	}

	/**
	 * @param verificationToken
	 * @return User
	 */
	@Override
	public User getUser(String verificationToken) {
		User user = tokenRepo.findByToken(verificationToken).getUser();
		return user;
	}

	/**
	 * @param argPhoneNumber
	 * @return User
	 */
	@Override
	public User findUserByPhoneNumber(String argPhoneNumber) {
		return userRepo.findByPhoneNumber(argPhoneNumber);
	}

	/**
	 * @param user
	 * @param token
	 */
	@Override
	public void createPasswordResetToken(User user, String token) {
		PasswordResetToken myToken = new PasswordResetToken(token, user);
		passwordTokenRepository.save(myToken);
	}

	/**
	 * @param token
	 * @return String
	 */
	@Override
	public String validatePasswordToken(String token) {
		final PasswordResetToken passToken = passwordTokenRepository.findByToken(token);
		return !isTokenFound(passToken) ? "invalidToken" : isTokenExpired(passToken) ? "expired" : "Valid";
	}

	/**
	 * @param passToken
	 * @return boolean
	 */
	private boolean isTokenFound(PasswordResetToken passToken) {
		return passToken != null;
	}

	/**
	 * @param passToken
	 * @return boolean
	 */
	private boolean isTokenExpired(PasswordResetToken passToken) {
		final Calendar cal = Calendar.getInstance();
		return passToken.getExpiryDate().before(cal.getTime());
	}

	/**
	 * @param token
	 * @return User
	 */
	@Override
	public User getUserByPasswordResetToken(String token) {
		PasswordResetToken passToken = this.passwordTokenRepository.findByToken(token);
		return passToken.getUser();
	}

	/**
	 * @param passEncoder
	 * @param user
	 * @param newPassword
	 */
	@Override
	public void changeUserPassword(PasswordEncoder passEncoder, User user, String newPassword) {
		user.setPassword(passEncoder.encode(newPassword));
		this.userRepo.save(user);
	}

}
