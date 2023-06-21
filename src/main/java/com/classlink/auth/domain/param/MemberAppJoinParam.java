package com.classlink.auth.domain.param;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MemberAppJoinParam {
	
	@JsonProperty("MemId")
	private String MemId;
	
	@JsonProperty("MemPwd")
	private String MemPwd;
	
	@JsonProperty("MemEmail")
	private String MemEmail;
}
