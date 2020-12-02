package com.userregisteration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

@SpringBootApplication
public class SimpleUserRegisterationService {

	
	/** 
	 * @return ResourceBundleMessageSource
	 */
	@Bean(name="messageSource")//wont work without the bean name
	public ResourceBundleMessageSource bundleMessageSource() {
	    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	    messageSource.setBasename("messages");
	    return messageSource;
	}
	
	
	/** 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(SimpleUserRegisterationService.class, args);
	}

}
