package com.siva.taskmanager.controller;

import com.siva.taskmanager.model.Priority;
import com.siva.taskmanager.model.Task;
import com.siva.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tasks")
@Validated
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @GetMapping
    public ResponseEntity<List<Task>> listTasks(@RequestParam(name = "dueBefore", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate dueBefore,
                                                @RequestParam(name = "priority", required = false) Priority priority,
                                                @RequestParam(name = "completed", required = false) Boolean completed) {
        return ResponseEntity.ok(taskService.getListOfTasks(dueBefore, priority, completed));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable("id") UUID id) {
        Optional<Task> task = taskService.getTask(id);
        return task.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable("id") UUID id, @Valid @RequestBody Task updatedTask) {
        return ResponseEntity.ok(taskService.updateTask(id, updatedTask));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") UUID id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok("Deleted the Task id: " + id + " successfully.");
    }
}
