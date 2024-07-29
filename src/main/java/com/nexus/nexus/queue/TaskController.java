package com.nexus.nexus.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskManager taskManager;

    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        try {
            taskManager.addTask(task);
            return ResponseEntity.ok(task);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> removeTask(@PathVariable String name) {
        try {
            Task task = taskManager.removeTaskByName(name);
            if (task != null) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Task>> listTasks() {
        try {
            List<Task> tasks = taskManager.listTasks();
            return ResponseEntity.ok(tasks);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
