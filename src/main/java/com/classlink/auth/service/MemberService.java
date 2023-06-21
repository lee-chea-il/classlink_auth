package com.classlink.auth.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.classlink.auth.domain.dto.ChangePwdDto;
import com.classlink.auth.domain.dto.MemberDto;
import com.classlink.auth.domain.dto.memberAppDto;
import com.classlink.auth.domain.param.ChangePwdParam;
import com.classlink.auth.domain.param.MemberAppJoinParam;
import com.classlink.auth.domain.param.MemberJoinParam;
import com.classlink.auth.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

	public final MemberMapper memberMapper;

	@Transactional
	public void memberJoinProc(MemberJoinParam memberJoinParam) {
		MemberDto memberDto = new MemberDto();
		BeanUtils.copyProperties(memberJoinParam, memberDto, "TermsAgreeYn", "PersonalYn");
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		if (memberJoinParam.isPersonalYn()) {
			memberDto.setPersonalYn(1);
		}
		if (memberJoinParam.isTermsAgreeYn()) {
			memberDto.setTermsAgreeYn(1);
		}

		memberDto.setMemPwd(passwordEncoder.encode(memberDto.getMemPwd()));
		memberMapper.insertMember(memberDto);
	}

	@Transactional
	public void changePwdProc(ChangePwdParam changePwdParam) {
		ChangePwdDto changePwdDto = new ChangePwdDto();
		BeanUtils.copyProperties(changePwdParam, changePwdDto);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		changePwdDto.setMemPwd(passwordEncoder.encode(changePwdDto.getMemPwd()));
		memberMapper.updatePassword(changePwdDto);
	}

	@Transactional
	public void memberAppJoinProc(MemberAppJoinParam memberAppJoinParam) {
		memberAppDto memberAppDto = new memberAppDto();
		BeanUtils.copyProperties(memberAppJoinParam, memberAppDto, "TermsAgreeYn", "PersonalYn");
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		memberAppDto.setPersonalYn(1);
		memberAppDto.setTermsAgreeYn(1);

		log.info("passwd ? : "+memberAppDto.getMemPwd());
		memberAppDto.setMemPwd(passwordEncoder.encode(memberAppDto.getMemPwd()));
		memberMapper.insertAppMember(memberAppDto);
	}
	
	public Map<String, Boolean> dupChkIdProc(String memId) {
		Map<String, Boolean> data = new HashMap<String, Boolean>();
		data.put("IsValid", (memberMapper.selectDupchkId(memId) > 0) ? false : true);
		
		return  data; 	
	}
}
