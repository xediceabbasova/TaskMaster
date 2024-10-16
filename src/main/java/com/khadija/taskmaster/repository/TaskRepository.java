package com.khadija.taskmaster.repository;

import com.khadija.taskmaster.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
