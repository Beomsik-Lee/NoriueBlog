package com.study.blog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.study.blog.model.RoleEntity;
import com.study.blog.model.UserEntity;
import com.study.blog.repository.NBUserRepository;

@Service
public class UserDetailsServiceImple implements UserDetailsService {
	
	@Autowired
	private NBUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("No user found with username: " + email));
		
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        
        return new User(user.getEmail(), 
        				user.getPassword(), 
        				enabled, 
        				accountNonExpired,	
    					credentialsNonExpired, 
    					accountNonLocked, 
    					getAuthorities((List<RoleEntity>) user.getRoles()));
	}
	
	private static List<GrantedAuthority> getAuthorities (List<RoleEntity> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (RoleEntity role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

}
