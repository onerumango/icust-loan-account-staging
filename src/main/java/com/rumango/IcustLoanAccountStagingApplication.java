package com.rumango;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableScheduling
public class IcustLoanAccountStagingApplication extends SpringBootServletInitializer{

	@Bean
	public ModelMapper modelMapper(){
	    return new ModelMapper();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(IcustLoanAccountStagingApplication.class, args);
	}

}
