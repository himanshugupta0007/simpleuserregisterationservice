/**
 * 
 */
package com.userregisteration.controller.advice;

import com.userregisteration.exception.UserAlreadyExistException;
import com.userregisteration.responseenvelope.RegisterResponseEnvelope;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author himanshugupta
 *
 */
@ControllerAdvice
public class UserRegisterationControllerAdvice {

	@ExceptionHandler(UserAlreadyExistException.class)
	public ResponseEntity<RegisterResponseEnvelope> handleRegisterUserException(final UserAlreadyExistException exception) {
		RegisterResponseEnvelope response = new RegisterResponseEnvelope("User Already Present",
				exception.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
}
