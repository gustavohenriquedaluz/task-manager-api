package com.example.demo.controller;

import com.example.demo.model.TaskEntity;
import com.example.demo.repository.TaskRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tasks")
public class TaskController {

    private final TaskRepository repository;

    public TaskController(TaskRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<TaskEntity>> getTasks(){
        return ResponseEntity.ok().body(repository.findAll());
    }

    @GetMapping("findById/{id}")
    public ResponseEntity<TaskEntity> getTaskById(@PathVariable Long id ){
        return ResponseEntity.ok().body(repository.getById(id));
    }

    @GetMapping("findByTitle/{title}")
    public ResponseEntity<List<TaskEntity>> getTaskByTitle(@PathVariable String title ){
        return ResponseEntity.ok().body(repository.findByTitle(title));
    }

    @GetMapping("findByDone/{done}")
    public ResponseEntity<List<TaskEntity>> getTaskByTitle(@PathVariable Boolean done ){
        return ResponseEntity.ok().body(repository.findByDone(done));
    }


    @PostMapping
    public ResponseEntity<TaskEntity> salvar (@RequestBody TaskEntity task){
        return ResponseEntity.ok().body(repository.save(task));
    }

}
