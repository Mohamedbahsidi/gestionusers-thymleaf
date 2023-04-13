package com.esp.gestionusers.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.esp.gestionusers.beans.Utilisateur;
import com.esp.gestionusers.dao.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
private PasswordEncoder passwordencoder;
	@Autowired
private	UserRepository userRepository ;
	
@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				Utilisateur userr= userRepository.findByUsername(username);
				return new User(userr.getUsername(), userr.getPassword(), Collections.emptyList());
			}
		};
		
		
		
		
		
	}
	
	
	//@Bean
   /*   public InMemoryUserDetailsManager inmemoryuserdetailmenager() {
		return new InMemoryUserDetailsManager(
				
				User.withUsername("med1").password(passwordencoder.encode("123")).roles("USER").build(),
				User.withUsername("med2").password(passwordencoder.encode("123")).roles("USER").build(),
				User.withUsername("admin").password(passwordencoder.encode("123")).roles("USER","ADMIN").build()
				);
		
		
	}*/
	
	//creation du filter
	@Bean
public SecurityFilterChain securityfilterchain(HttpSecurity httpsecurity) throws Exception {
		httpsecurity.formLogin().loginPage("/login").permitAll();
		//httpsecurity.authorizeHttpRequests().requestMatchers("/user/**").hasRole("USER");
		//httpsecurity.authorizeHttpRequests().requestMatchers("/admin/**").hasRole("ADMIN");
		httpsecurity.authorizeHttpRequests().requestMatchers("/css/**").permitAll();
		httpsecurity.authorizeHttpRequests().requestMatchers("/webjars/**").permitAll();
		httpsecurity.authorizeHttpRequests().anyRequest().authenticated();
	httpsecurity.exceptionHandling().accessDeniedPage("/notAuthorized");
	return httpsecurity.build();
	
	
}




}
