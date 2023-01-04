package com.classlink.auth.service.login;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.classlink.auth.domain.CustomUserDetails;
import com.classlink.auth.service.CustomUserDetailsService;

@Slf4j
public class CustomAuthenticationProvider extends DaoAuthenticationProvider {
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		CustomUserDetails user = null;
		String userId = (String) auth.getPrincipal();
		String password = (String) auth.getCredentials();

		log.info("login info : [" + userId + "], [" + password + "]");

		try {
			user = (CustomUserDetails) customUserDetailsService.loadUserByUsername(userId);
			log.info("password info : [" + password + "], [" + user.getPassword() + "]");
			
			// if (!(bCryptPasswordEncoder.matches(password, user.getPassword()))) {
			if (!matchPassword(password, user.getPassword())) {
				throw new BadCredentialsException(userId);
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
			throw new BadCredentialsException(ex.getMessage());
		}

		UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(userId, password,
				user.getAuthorities());
		log.debug(">>>>>>>>> Authentication is success >>>>>>>>>>");

		return upat;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

	private boolean matchPassword(String loginPassword, String password) {		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.matches(loginPassword, password);
	}
}
