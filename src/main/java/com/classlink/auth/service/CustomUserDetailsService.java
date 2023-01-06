package com.classlink.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.classlink.auth.domain.CustomUserDetails;
import com.classlink.auth.mapper.CustomUserDetailsMapper;

/**
 * @author redcore 
 * 인증관련 커스텀 유저정보 클래스
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	
	private final CustomUserDetailsMapper customUserDetailsMapper;

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		log.info("Authenticating user with username={}", userId);

		CustomUserDetails customUserDetails = customUserDetailsMapper.findCustomUserDetailsById(userId);
		log.info(customUserDetailsMapper.findCustomUserDetailsById(userId).toString());
		//customUserDetails.setMem_grpList(null);
		if (customUserDetails == null) {
			throw new UsernameNotFoundException("여기냐?");
		}
		
		log.info(customUserDetails.toString());
		
		return customUserDetails;
	}
}
