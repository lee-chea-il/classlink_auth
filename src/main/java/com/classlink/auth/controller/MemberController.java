package com.classlink.auth.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.bouncycastle.jcajce.provider.asymmetric.dsa.DSASigner.stdDSA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.classlink.auth.domain.dto.ChangePwdDto;
import com.classlink.auth.domain.param.ChangePwdParam;
import com.classlink.auth.domain.param.MemberAppJoinParam;
import com.classlink.auth.domain.param.MemberJoinParam;
import com.classlink.auth.domain.result.ResponseResult;
import com.classlink.auth.service.MemberService;

import io.swagger.annotations.ApiModelProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	
	@ApiModelProperty(value = "회원가입", notes = "웹에서 사용되는 API 입니다.")
	@PostMapping(value = "/join")
	public ResponseResult<?> memberJoin (@RequestBody MemberJoinParam param, HttpServletRequest request) {
		memberService.memberJoinProc(param);
		return new ResponseResult<>(HttpStatus.OK.value());
	}
	
	@ApiModelProperty(value = "비밀번호 변경", notes = "")
	@PostMapping(value = "/changePwd")
	public ResponseResult<?> changePwd (@RequestBody ChangePwdParam param, HttpServletRequest request) {
		memberService.changePwdProc(param);
		return new ResponseResult<>(HttpStatus.OK.value());
	}
	
	@ApiModelProperty(value = "app 회원가입", notes = "웹에서는 사용되지 않습니다.")
	@PostMapping(value = "/appJoin")
	public ResponseResult<?> memberAppJoin (@RequestBody MemberAppJoinParam param, HttpServletRequest request) {
		memberService.memberAppJoinProc(param);
		return new ResponseResult<>(HttpStatus.OK.value());
	}
	
	@ApiModelProperty(value = "회원가입시 아이디 중복 체크", notes = "")
	@PostMapping(value = "/dupChkId")
	public ResponseResult<?> dupChkId (@RequestBody Map<String, String> param, HttpServletRequest request) {
		Map<String, Boolean> data = memberService.dupChkIdProc(param.get("memId"));
		return new ResponseResult<>(HttpStatus.OK.value(), data);
	}
}



