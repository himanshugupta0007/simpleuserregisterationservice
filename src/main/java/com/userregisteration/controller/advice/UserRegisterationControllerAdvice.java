/**
 * 
 */
package com.userregisteration.controller.advice;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.userregisteration.exception.UserAlreadyExistException;
import com.userregisteration.responseenvelope.RegisterResponseEnvelope;

/**
 * @author himanshugupta
 *
 */
@ControllerAdvice
public class UserRegisterationControllerAdvice {

	@ExceptionHandler(UserAlreadyExistException.class)
	public ResponseEntity handleRegisterUserException(final UserAlreadyExistException exception) {
		RegisterResponseEnvelope response = new RegisterResponseEnvelope("User Already Present",
				exception.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
}
