package com.lsm.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class UserConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	CustomSuccessHandler customSuccessHandler;

	@Bean
	public UserDetailsService getUserDetailService() {
		return new UserDetailServiceImpl();
	}
	/*
	 * @Bean public PasswordEncoder passwordEncoder() { return
	 * NoOpPasswordEncoder.getInstance(); }
	 */

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(getUserDetailService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	/*
	 * @Override public void configure(AuthenticationManagerBuilder auth) throws
	 * Exception {
	 * 
	 * auth.inMemoryAuthentication().withUser("librarian").password("{noop}password"
	 * ).roles("USER").and()
	 * .withUser("admin").password("{noop}password").roles("ADMIN"); }
	 */

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN").antMatchers("/user/**").hasRole("USER")
				.antMatchers("/**").permitAll().and().formLogin().loginPage("/login").loginProcessingUrl("/login")
				.successHandler(customSuccessHandler).and().csrf().disable();
	}

}
