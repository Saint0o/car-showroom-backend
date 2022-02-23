package com.carshowroom.project.carshowroomproject.repositories;

import com.carshowroom.project.carshowroomproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String name);
}
