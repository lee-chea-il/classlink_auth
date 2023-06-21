package com.classlink.auth.domain.dto;

import lombok.Data;

@Data
public class memberAppDto {
	private String MemId;
	private String MemPwd;
	private String MemEmail;
	private int TermsAgreeYn = 0;
	private int PersonalYn = 0;
}
