package com.userregisteration.listener;

import java.time.LocalDateTime;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.userregisteration.modal.User;

public class UserRegisterationListener {

	
	/** 
	 * @param arguser
	 */
	@PrePersist
	public void prePersist(User arguser) {
		arguser.setCreatedOn(LocalDateTime.now());
		arguser.setUpdatedOn(LocalDateTime.now());
	}

	
	/** 
	 * @param argUser
	 */
	@PreUpdate
	public void preUpdate(User argUser) {
		argUser.setUpdatedOn(LocalDateTime.now());
	}
}
