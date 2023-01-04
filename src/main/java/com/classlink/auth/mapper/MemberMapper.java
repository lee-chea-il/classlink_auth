package com.classlink.auth.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.classlink.auth.domain.dto.ChangePwdDto;
import com.classlink.auth.domain.dto.MemberDto;

@Mapper
public interface MemberMapper {
	public int insertMember(MemberDto memberDto );
	public int updatePassword(ChangePwdDto changePwdDto );
}
