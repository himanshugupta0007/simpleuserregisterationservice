/**
 * 
 */
package com.userregisteration.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.userregisteration.modal.User;

/**
 * @author himanshugupta
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {

	User findByPhoneNumber(String phoneNumber);
	
	User findByEmailAddress(String emailAddress);
}
