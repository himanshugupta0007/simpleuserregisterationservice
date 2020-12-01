package com.userregisteration.listener;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.userregisteration.modal.User;

public class UserRegisterationListener {

	@PrePersist
	public void prePersist(User arguser) {
		arguser.setCreatedOn(LocalDateTime.now());
		arguser.setUpdatedOn(LocalDateTime.now());
	}

	@PreUpdate
	public void preUpdate(User argUser) {
		argUser.setUpdatedOn(LocalDateTime.now());
	}
}
