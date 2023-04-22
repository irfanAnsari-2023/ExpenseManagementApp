package com.expense.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig  {
//	this is the basic security approach and we can enhance it acc. to our requirements
	public void configure (HttpSecurity http) throws Exception{
		http
			.authorizeRequests()
			// this will help in accessing API's without authentication
			// either we can do it one by one or we can mention all them using commas seperation
			//.antMatchers("/userId","/unpaid","/create").permitAll()
			
			// we can make our task more easier if we use this kind of thing as mentioned in below line 
			// since it is very hectic to change the URL again and again so we simply put/say that all the URLs which is starting from /public can be accessible by anyone
			.requestMatchers("/api/**").permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.httpBasic();
	}
	// creating user with password and role
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		
		
		// With BcryptPasswordEncoder : this will make our password more complex and strong 
		auth.inMemoryAuthentication().withUser("Irfan").password(this.getPasswordEncoder().encode("Irfan@123")).roles("normal_role");
		auth.inMemoryAuthentication().withUser("Rihan").password(this.getPasswordEncoder().encode("Rihan@123")).roles("admin_role");
// lets say we want that some API can handle by admin only and rest APi are handle by normal user then
	}
	@Bean
    public PasswordEncoder getPasswordEncoder(){
       // return NoOpPasswordEncoder.getInstance();
		// with BcryptPasswordEncoder 
		return new BCryptPasswordEncoder(10);	
    }
}
