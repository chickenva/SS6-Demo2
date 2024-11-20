package com.itproject.vn.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.itproject.vn.entity.UserInfo;
import com.itproject.vn.repository.UserInfoRepository;

public class UserInfoService {
	@Autowired
    UserInfoRepository repository;
    
    public UserInfoService(UserInfoRepository userInfoRepository) {
        this.repository = userInfoRepository;
    }
    
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = repository.findByName(username);
        return userInfo.map(UserInfoUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found: " + username));
    }
}
