package com.classlink.auth.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.classlink.auth.domain.CustomUserDetails;

@Mapper
public interface CustomUserDetailsMapper {
	public CustomUserDetails findCustomUserDetailsById(String memId);
}
