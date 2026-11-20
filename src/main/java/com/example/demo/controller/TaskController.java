package com.example.demo.controller;

import com.example.demo.model.TaskEntity;
import com.example.demo.repository.TaskRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("tasks")
public class TaskController {

    private final TaskRepository repository;

    public TaskController(TaskRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<TaskEntity>> getTasks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Boolean done) {
        if (title != null && !title.isBlank()) {
            return ResponseEntity.ok().body(repository.findByTitle(title));
        }

        if (done != null) {
            return ResponseEntity.ok().body(repository.findByDone(done));
        }

        return ResponseEntity.ok().body(repository.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<TaskEntity> getTaskById(@PathVariable Long id) {
        Optional<TaskEntity> find = repository.findById(id);

        if (find.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(find.get());
    }

    @PostMapping
    public ResponseEntity<TaskEntity> save(@RequestBody TaskEntity task) {
        return ResponseEntity.ok().body(repository.save(task));
    }

    @PutMapping("{id}")
    public ResponseEntity<TaskEntity> update(@PathVariable Long id, @RequestBody TaskEntity newData) {

        if (newData.getTitle() == null || newData.getDone() == null || newData.getTitle().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        Optional<TaskEntity> find = repository.findById(id);

        if (find.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        TaskEntity taskFound = find.get();

        taskFound.setTitle(newData.getTitle());
        taskFound.setDone(newData.getDone());

        return ResponseEntity.ok(repository.save(taskFound));
    }

}
