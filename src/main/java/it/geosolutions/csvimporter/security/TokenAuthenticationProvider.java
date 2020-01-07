package it.geosolutions.csvimporter.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {
    
	@Autowired
	private Environment env;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		Object principal = authentication.getPrincipal();
		if (principal !=null) {
			String token = String.valueOf(principal);
			if(token.equals(env.getProperty("token"))){
				
				authentication = new UsernamePasswordAuthenticationToken(token, token,
						Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
			}
		} else {
			throw new BadCredentialsException("Invalid authentication token");
		}
		return authentication;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (Authentication.class.isAssignableFrom(authentication));
	}
	
	

	

}
