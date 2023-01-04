package com.classlink.auth.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.classlink.auth.common.CommonConst;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomUserDetails extends BaseObject implements UserDetails {
	private String id;
	private String pwd;
	private List<String> mem_grpList;	
	private String isValid;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();

		return authList;
	}

	public List<String> getMem_grpList() {
		return mem_grpList;
	}

	public void setMem_grpList(List<String> mem_grpList) {
		this.mem_grpList = mem_grpList;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return pwd;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		if ((isValid != null) && isValid.equals(CommonConst.StatusYn.Y.name())) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}
}
