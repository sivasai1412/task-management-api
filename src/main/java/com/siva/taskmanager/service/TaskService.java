package com.siva.taskmanager.service;

import com.siva.taskmanager.model.Priority;
import com.siva.taskmanager.model.Task;
import com.siva.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> getListOfTasks(LocalDate dueBefore, Priority priority, Boolean completed) {
        return taskRepository.findAll().stream()
                .filter(i -> dueBefore == null || !i.getDueDate().isAfter(dueBefore))
                .filter(i -> priority == null || i.getPriority() == priority)
                .filter(i -> completed == null || i.isCompleted() == completed)
                .toList();
    }

    public Optional<Task> getTask(UUID id) {
        return taskRepository.findById(id);
    }

    public Task updateTask(UUID id, Task updatedTask) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setDueDate(updatedTask.getDueDate());
        task.setPriority(updatedTask.getPriority());
        task.setCompleted(updatedTask.isCompleted());
        return taskRepository.save(task);
    }

    public void deleteTask(UUID id) {
        taskRepository.deleteById(id);
    }
}
