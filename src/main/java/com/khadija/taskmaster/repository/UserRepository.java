package com.khadija.taskmaster.repository;

import com.khadija.taskmaster.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
