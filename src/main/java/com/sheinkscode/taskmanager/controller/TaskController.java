package com.sheinkscode.taskmanager.controller;

import com.sheinkscode.taskmanager.model.Task;
import com.sheinkscode.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/tasks")
public class TaskController {
 private final TaskService taskService;


    public TaskController(TaskService taskService){
     this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(){

        List<Task> tasks = taskService.getAllTasks();

        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {

        Task task = taskService.getTaskById(id);

        if(task == null){
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(task);
        }
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody @Valid Task newTask){
        Task taskSaved = taskService.createTask(newTask);

        return ResponseEntity.status(HttpStatus.CREATED).body(taskSaved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id){

        boolean deleted = taskService.deleteTask(id);

        if(deleted){
            return ResponseEntity.ok("tasken med ID: " + id + " ble slettet");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Kunne ikke slette: tasken med ID " + id + " finnes ikke");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id,@RequestBody @Valid Task updatedTask){

        Task task = taskService.updateTask(id, updatedTask);

        if(task == null){
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(task);
        }

    }

}

