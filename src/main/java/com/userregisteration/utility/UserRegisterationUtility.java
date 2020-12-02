package com.userregisteration.utility;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class UserRegisterationUtility {

    @Autowired
    private JavaMailSender mailSender;

    /**
     * @param recipientAddress
     * @param subject
     * @param message
     */
    public void sendmail(String recipientAddress, String subject, String message) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message);
        try {
            mailSender.send(email);
        } catch (Exception ex) {
            // do nothing
        }
    }

    /**
     * @param request
     * @return String
     */
    public String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
