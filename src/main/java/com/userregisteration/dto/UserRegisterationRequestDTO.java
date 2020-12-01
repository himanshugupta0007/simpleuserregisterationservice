/**
 * 
 */
package com.userregisteration.dto;

import javax.validation.constraints.NotNull;

/**
 * @author himanshugupta
 *
 */
public class UserRegisterationRequestDTO {

	@NotNull
	UserDTO userDTO;

	/**
	 * @return the userDTO
	 */
	public UserDTO getUserDTO() {
		return userDTO;
	}

	/**
	 * @param userDTO the userDTO to set
	 */
	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	@Override
	public String toString() {
		return "UserRegisterationRequestDTO [userDTO=" + userDTO + "]";
	}

}
