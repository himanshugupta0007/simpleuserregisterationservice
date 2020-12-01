/**
 * 
 */
package com.userregisteration.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userregisteration.modal.User;
import com.userregisteration.modal.VerificationToken;

/**
 * @author himanshugupta
 *
 */
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
	
    VerificationToken findByToken(String token);
    
    VerificationToken findByUser(User user);

}
