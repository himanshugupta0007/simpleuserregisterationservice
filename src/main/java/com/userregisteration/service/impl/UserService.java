/**
 * 
 */
package com.userregisteration.service.impl;

import java.util.Arrays;

import javax.transaction.Transactional;

import com.userregisteration.modal.User;
import com.userregisteration.modal.VerificationToken;
import com.userregisteration.repo.RoleRepository;
import com.userregisteration.repo.UserRepository;
import com.userregisteration.repo.VerificationTokenRepository;
import com.userregisteration.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
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

	/**
	 * @param userRepo
	 */
	@Autowired
	public UserService(final UserRepository userRepo, final RoleRepository roleRepo, final VerificationTokenRepository tokenRepo) {
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
		this.tokenRepo = tokenRepo;
	}

	@Transactional
	@Override
	public User registeruser(User argUser) {
			if(argUser.getRoles() == null)
			{
				argUser.setRoles(Arrays.asList(roleRepo.findByName("ROLE_USER")));
			}
			return this.userRepo.saveAndFlush(argUser);
		

	}

	@Override
	public boolean userExists(String phone_number) {
		return userRepo.findByPhoneNumber(phone_number) != null;
	}

	@Override
	public void createVerificationToken(User user, String token) {
		VerificationToken myToken = new VerificationToken(token, user);
        tokenRepo.save(myToken);
		
	}

	@Override
	public VerificationToken getVerificationToken(String VerificationToken) {
		return tokenRepo.findByToken(VerificationToken);
	}

	@Override
	public User getUser(String verificationToken) {
		User user = tokenRepo.findByToken(verificationToken).getUser();
        return user;
	}

}
