package com.carshowroom.project.carshowroomproject.service;

import com.carshowroom.project.carshowroomproject.entities.User;

import java.util.List;

public interface UserService {

    User register(User user);

    List<User> getAll();

    User findByUsername(String username);

    User findById(Long id);

    void delete(Long id);
}
