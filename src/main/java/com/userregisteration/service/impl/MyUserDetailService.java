/**
 * 
 */
package com.userregisteration.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.userregisteration.modal.Privilege;
import com.userregisteration.modal.Role;
import com.userregisteration.modal.User;
import com.userregisteration.repo.RoleRepository;
import com.userregisteration.repo.UserRepository;
import com.userregisteration.service.IUserService;

/**
 * @author himanshugupta
 *
 */
@Service
@Transactional
public class MyUserDetailService implements UserDetailsService {

	UserRepository userRepo;

	RoleRepository roleRepo;

	IUserService userService;

	/**
	 * @param userRepo
	 * @param roleRepo
	 * @param userService
	 */
	@Autowired
	public MyUserDetailService(final UserRepository userRepo, final RoleRepository roleRepo,
			final IUserService userService) {
		super();
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
		this.userService = userService;
	}

	/**
	 *
	 */
	@Override
	public UserDetails loadUserByUsername(String argPhonenumber) throws UsernameNotFoundException {
		User user = userRepo.findByPhoneNumber(argPhonenumber);
		if (user == null) {
			throw new UsernameNotFoundException("User with Phone Number:" + argPhonenumber + "not found");
		}

		return new org.springframework.security.core.userdetails.User(user.getEmailAddress(), user.getPassword(),
				user.isEnabled(), true, true, true, getAuthorities(user.getRoles()));
	}

	/**
	 * @param roles
	 * @return
	 */
	private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {

		return getGrantedAuthorities(getPrivileges(roles));
	}

	/**
	 * @param roles
	 * @return
	 */
	private List<String> getPrivileges(Collection<Role> roles) {

		List<String> privileges = new ArrayList<>();
		List<Privilege> collection = new ArrayList<>();
		for (Role role : roles) {
			collection.addAll(role.getPrivileges());
		}
		for (Privilege item : collection) {
			privileges.add(item.getName());
		}
		return privileges;
	}

	/**
	 * @param privileges
	 * @return
	 */
	private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (String privilege : privileges) {
			authorities.add(new SimpleGrantedAuthority(privilege));
		}
		return authorities;
	}
}
