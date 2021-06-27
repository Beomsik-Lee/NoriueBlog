package com.study.blog.service;

import com.study.blog.model.UserDto;
import com.study.blog.model.UserEntity;

public interface NBUserService {
	void saveUser(UserEntity user);
	UserEntity registerNewUserAccount(UserDto userDto);
}
