package com.userregisteration.dto;

import com.userregisteration.validator.*;
/**
 * PasswordDto
 */
@PasswordValidator
public class PasswordDto {

    String oldPassword;
    String newPassword;
    String token;

    /**
     * @return String
     */
    public String getOldPassword() {
        return oldPassword;
    }

    /**
     * @param oldPassword
     */
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    /**
     * @return String
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * @param newPassword
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    /**
     * @return String
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return int
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((newPassword == null) ? 0 : newPassword.hashCode());
        result = prime * result + ((oldPassword == null) ? 0 : oldPassword.hashCode());
        result = prime * result + ((token == null) ? 0 : token.hashCode());
        return result;
    }

    /**
     * @param obj
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PasswordDto other = (PasswordDto) obj;
        if (newPassword == null) {
            if (other.newPassword != null)
                return false;
        } else if (!newPassword.equals(other.newPassword))
            return false;
        if (oldPassword == null) {
            if (other.oldPassword != null)
                return false;
        } else if (!oldPassword.equals(other.oldPassword))
            return false;
        if (token == null) {
            if (other.token != null)
                return false;
        } else if (!token.equals(other.token))
            return false;
        return true;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "PasswordDto [newPassword=" + newPassword + ", oldPassword=" + oldPassword + ", token=" + token + "]";
    }

}