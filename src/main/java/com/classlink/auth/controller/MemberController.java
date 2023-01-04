package com.classlink.auth.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.classlink.auth.domain.dto.ChangePwdDto;
import com.classlink.auth.domain.param.ChangePwdParam;
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
	
	@ApiModelProperty(value = "회원가입", notes = "")
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
}
