/**
 * 
 */
package com.userregisteration.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.userregisteration.modal.Privilege;

/**
 * @author himanshugupta
 *
 */
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
	
	Privilege findByName(String name);
}
