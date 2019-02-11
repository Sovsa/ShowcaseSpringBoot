package com.sato.sosplatform;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;




@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter  {
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
			.authorizeRequests()
				.antMatchers("/", 
						"/en/registerbp", 
						"/en/registerec", 
						"/login", 
						"/css/**", 
						"/js/**",
						"/resources/**").permitAll()
				.antMatchers("/en/viewregistry").hasRole("USER")
                .anyRequest().authenticated()
                .and()
            .formLogin()
            	.loginPage("//login")
            	.permitAll()
            	.and()
            .logout()
            	.permitAll();
	}
	
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("s@to4all123").password("lthyz5746").roles("USER").build());
        
        return manager;
    }
}