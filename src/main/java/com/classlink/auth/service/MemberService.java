package com.classlink.auth.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.classlink.auth.domain.dto.ChangePwdDto;
import com.classlink.auth.domain.dto.MemberDto;
import com.classlink.auth.domain.param.ChangePwdParam;
import com.classlink.auth.domain.param.MemberJoinParam;
import com.classlink.auth.mapper.MemberMapper;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class MemberService {
	
	//@Autowired
	//BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	MemberMapper memberMapper;
	
	@Transactional
	public void memberJoinProc(MemberJoinParam memberJoinParam) {
		MemberDto memberDto = new MemberDto();
		BeanUtils.copyProperties(memberJoinParam,  memberDto);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		memberDto.setMemPwd(passwordEncoder.encode(memberDto.getMemPwd()));
		memberMapper.insertMember(memberDto);
	}
	
	@Transactional
	public void changePwdProc(ChangePwdParam changePwdParam) {
		ChangePwdDto changePwdDto  = new ChangePwdDto();
		BeanUtils.copyProperties(changePwdParam,  changePwdDto);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		changePwdDto.setMemPwd(passwordEncoder.encode(changePwdDto.getMemPwd()));
		memberMapper.updatePassword(changePwdDto);
	}
}
