package com.classlink.auth.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.classlink.auth.domain.dto.ChangePwdDto;
import com.classlink.auth.domain.dto.MemberDto;
import com.classlink.auth.domain.dto.memberAppDto;

@Mapper
public interface MemberMapper {
	public int selectDupchkId(String memId);
	public int insertMember(MemberDto memberDto );
	public int insertAppMember(memberAppDto memberAppDto );
	public int updatePassword(ChangePwdDto changePwdDto );
}
