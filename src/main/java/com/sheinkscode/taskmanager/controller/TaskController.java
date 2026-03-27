package com.sheinkscode.taskmanager.controller;

import com.sheinkscode.taskmanager.model.Task;
import com.sheinkscode.taskmanager.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/tasks")
public class TaskController {
 final TaskService taskService;


    public TaskController(TaskService taskService){
     this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAllTasks(){
     return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable int id) {
        return taskService.getTaskById(id);
    }

    @PostMapping
    public Task createTask(@RequestBody Task newTask){
        return taskService.createTask(newTask);
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable int id){
        //return taskService.deleteTask(id);

        boolean deleted = taskService.deleteTask(id);

        if (deleted){
            return  "Tasken med id " + id + " ble slettet.";
        }else {
            return "For en eller anne grunn så er tasken med id " + id + " ikke slettet";
        }
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable int id,@RequestBody Task updatedTask){

        return taskService.updateTask(id, updatedTask);

    }

}

