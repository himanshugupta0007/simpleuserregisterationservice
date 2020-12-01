/**
 * 
 */
package com.userregisteration.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.userregisteration.modal.Role;

/**
 * @author himanshugupta
 *
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Role findByName(String name);
}
