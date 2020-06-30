package com.todoapp.app.restcontroller;

import com.todoapp.app.entity.Task;
import com.todoapp.app.repository.Task_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TaskResource {

    @Autowired
    Task_Repository task_repository;

    @GetMapping( "/users/{user_id}/pending_tasks" )
    public Optional<List<Task>> get_pending_tasks(@PathVariable int id ){

        return task_repository.findByTidAndTask_status( id , "Pending" );
    }

    @PostMapping( "/users/{user_id}/tasks" )
    public void add_task(@PathVariable int id , @RequestBody Task task ){

        task_repository.save( task );
    }

    @DeleteMapping( "/users/{user_id}/task/{task_id}" )
    public void delete_pending_task( @PathVariable int user_id , @PathVariable int task_id ){

        task_repository.deleteById( task_id );
    }

    @GetMapping( "/users/{user_id}/completed_tasks" )
    public Optional<List<Task>> get_completed_tasks(@PathVariable int id ){

        return task_repository.findByTidAndTask_status( id , "Completed" );
    }

    @PutMapping( "/users/{user_id}/tasks/{task_id}/{current_status}" )
    public void change_task_status(@PathVariable int id , @PathVariable int task_id , @PathVariable String current_status ){


    }



}
