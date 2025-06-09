package com.siva.taskmanager.repository;

import com.siva.taskmanager.model.Priority;
import com.siva.taskmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

    List<Task> findByDueDateBeforeAndCompletedFalse(LocalDate date);
    List<Task> findByDueDateBeforeAndPriorityAndCompleted(LocalDate date, Priority priority, boolean completed);
}
