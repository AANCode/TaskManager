package com.sheinkscode.taskmanager.service;

import com.sheinkscode.taskmanager.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    private final List<Task> tasks = new ArrayList<>();

    private Integer nextId = 4;


    public TaskService(){
        tasks.add(new Task(
                1,
                "Lære Spring Boot",
                "Forstå controller og service",
                false
        ));
        tasks.add(new Task(
                2,
                "Bygge Task API",
                "Lag første endpoint",
                false
        ));
        tasks.add(new Task(
                3,
                "Teste i Postman",
                "Sjekke at /task virker",
                true
        ));
    }


    public List<Task> getAllTasks(){
        return tasks;
    }

    public Task getTaskById(Integer id){
        for (Task task: tasks){
            if(task.getId() == id){
                return task;
            }
        }
        return null;
    }

    public Task createTask(Task newTask){
        newTask.setId(nextId);
        tasks.add(newTask);
        nextId++;
        return newTask;
    }

    //public boolean deleteTask(int id){
    //    return tasks.removeIf(task -> task.getId() == id);
    //}

    public boolean deleteTask(Integer id){
        Task taskfound = getTaskById(id);
        if(taskfound != null){
            tasks.remove(taskfound);
            return true;
        }
        return false;
    }


    public Task updateTask(Integer id, Task updatedTask){
        Task existingTask = getTaskById(id);

        if(existingTask != null){
            existingTask.setTitle(updatedTask.getTitle());
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setCompleted(updatedTask.isCompleted());

            return existingTask;
        }

        return null;
    }

}
