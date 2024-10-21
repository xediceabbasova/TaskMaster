package com.khadija.taskmaster.repository;

import com.khadija.taskmaster.model.Task;
import com.khadija.taskmaster.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByUser(User user);
}
