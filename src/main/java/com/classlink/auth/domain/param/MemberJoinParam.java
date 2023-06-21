package com.classlink.auth.domain.param;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MemberJoinParam {
	@JsonProperty("MemId")
	private String MemId;
	
	@JsonProperty("MemPwd")
	private String MemPwd;
	
	@JsonProperty("MemName")
	private String MemName;
	
	@JsonProperty("MemNickname")
	private String MemNickname;
	
	@JsonProperty("MemEmail")
	private String MemEmail;
	
	@JsonProperty("IdtCode")
	private String IdtCode;
	
	@JsonProperty("TermsAgreeYn")
	private boolean TermsAgreeYn;
	
	@JsonProperty("PersonalYn")
	private boolean PersonalYn;
}
