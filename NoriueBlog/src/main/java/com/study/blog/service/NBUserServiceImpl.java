package com.study.blog.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.study.blog.exception.UserAlreadyExistException;
import com.study.blog.model.UserEntity;
import com.study.blog.model.RoleEntity;
import com.study.blog.model.UserDto;
import com.study.blog.repository.NBUserRepository;

@Service
public class NBUserServiceImpl implements NBUserService {
	
	@Autowired
	NBUserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void saveUser(UserEntity user) {
		userRepository.save(user);
	}

	@Override
	public UserEntity registerNewUserAccount(UserDto userDto) throws UserAlreadyExistException {
		if (emailExist(userDto.getEmail())) {
            throw new UserAlreadyExistException(userDto.getEmail());
        }

        UserEntity user = new UserEntity();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setRoles(Arrays.asList(new RoleEntity("ROLE_USER")));

        return userRepository.save(user);
	}
	
	private boolean emailExist(String email) {
		return userRepository.findByEmail(email).isPresent();
	}
}
