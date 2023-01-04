package com.classlink.auth.domain.dto;

import lombok.Data;

@Data
public class MemberDto {
	private String MemId;
	private String MemPwd;
	private String MemName;
	private String MemNickname;
	private String MemEmail;
	private String IdtCode;
	private String TermsAgreeYn;
	private String PersonalYn;
}
