/**
 * 
 */
package com.userregisteration.setup;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.userregisteration.modal.Privilege;
import com.userregisteration.modal.Role;
import com.userregisteration.modal.User;
import com.userregisteration.repo.PrivilegeRepository;
import com.userregisteration.repo.RoleRepository;
import com.userregisteration.repo.UserRepository;

/**
 * @author himanshugupta This calls is just to set up the initial data for User,
 *         Roles and Privilegs
 */
@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	boolean alreadySetup = false;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PrivilegeRepository privilegeRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	
	/** 
	 * @param event
	 */
	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (alreadySetup)
			return;
		Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
		Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

		List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);
		createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
		createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));

		Role adminRole = roleRepository.findByName("ROLE_ADMIN");
		User user = new User();
		user.setFirstName("Test");
		user.setLastName("Test");
		user.setPassword(passwordEncoder.encode("test"));
		user.setEmailAddress("test@test.com");
		user.setRoles(Arrays.asList(adminRole));
		user.setEnabled(true);
		user.setPhoneNumber("9725206448");
		userRepository.save(user);
		alreadySetup = true;
	}
	
	
	/** 
	 * @param name
	 * @return Privilege
	 */
	@Transactional
    Privilege createPrivilegeIfNotFound(String name) {
 
        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }
 
    
	/** 
	 * @param name
	 * @param privileges
	 * @return Role
	 */
	@Transactional
    Role createRoleIfNotFound(
      String name, Collection<Privilege> privileges) {
 
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }

}
