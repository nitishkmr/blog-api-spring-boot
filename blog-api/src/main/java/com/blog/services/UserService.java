package com.blog.services;

import com.blog.payload.UserDto;

import java.util.List;

public interface UserService {

    UserDto registerNewUser(UserDto user);

    UserDto createUser(UserDto user);

    UserDto updateUser(UserDto user, Integer userId);

    UserDto getUserById(Integer id);

    List<UserDto> getAllUsers();

    void deleteUser(Integer userId);

}
