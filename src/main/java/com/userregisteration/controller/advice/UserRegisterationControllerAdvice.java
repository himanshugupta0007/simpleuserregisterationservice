/**
 * 
 */
package com.userregisteration.controller.advice;

import com.userregisteration.exception.PasswordDidNotMatchException;
import com.userregisteration.exception.UserAlreadyExistException;
import com.userregisteration.exception.UserNotFoundException;
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

	/**
	 * @param exception
	 * @return ResponseEntity<RegisterResponseEnvelope>
	 */
	@ExceptionHandler(UserAlreadyExistException.class)
	public ResponseEntity<RegisterResponseEnvelope> handleRegisterUserException(
			final UserAlreadyExistException exception) {
		RegisterResponseEnvelope response = new RegisterResponseEnvelope("User Already Present",
				exception.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	/**
	 * @param exception
	 * @return ResponseEntity<RegisterResponseEnvelope>
	 */
	@ExceptionHandler(PasswordDidNotMatchException.class)
	public ResponseEntity<RegisterResponseEnvelope> handleMismatchPasswordException(
			final PasswordDidNotMatchException exception) {
		RegisterResponseEnvelope response = new RegisterResponseEnvelope("Mis-Macth Password", exception.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	/**
	 * @param exception
	 * @return ResponseEntity<RegisterResponseEnvelope>
	 */
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<RegisterResponseEnvelope> handleuserNotFoundException(final UserNotFoundException exception) {
		RegisterResponseEnvelope response = new RegisterResponseEnvelope("User Not Found", exception.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
}
