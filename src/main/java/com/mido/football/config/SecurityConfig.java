package com.mido.football.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter{

	@Autowired
	private DataSource myDataSource;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.jdbcAuthentication().dataSource(myDataSource);
	}
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		try {
			String[] pathArray = new String[]{"/leagues/**","/clubs/**"};
			http.authorizeRequests()
				.antMatchers(pathArray).authenticated()
				.antMatchers("/resources/**").permitAll()
				.and()
					.formLogin().loginPage("/login").failureUrl("/login?error")
					  .usernameParameter("username").passwordParameter("password")
					.and()
					  .logout()
					  .invalidateHttpSession(true)
	                  .clearAuthentication(true)
	                  .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					  .logoutSuccessUrl("/login?logout")
					.and()
					  .exceptionHandling().accessDeniedPage("/access-denied")
					.and()
					  .csrf();
				//.exceptionHandling().accessDeniedPage("/access-denied");
		} catch (Exception e) {
			 e.printStackTrace();
		}
			
	}
	

	@Bean
	public UserDetailsManager userDetailsManager() {
		
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
		
		jdbcUserDetailsManager.setDataSource(myDataSource);
		
		return jdbcUserDetailsManager; 
	}
	
}
