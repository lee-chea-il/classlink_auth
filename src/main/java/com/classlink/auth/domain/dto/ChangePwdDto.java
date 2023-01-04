package com.classlink.auth.domain.dto;

import lombok.Data;

@Data
public class ChangePwdDto {
	private String MemId;
	private String MemPwd;
}
