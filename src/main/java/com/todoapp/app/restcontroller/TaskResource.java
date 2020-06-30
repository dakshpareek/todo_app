package com.todoapp.app.restcontroller;

import com.todoapp.app.entity.Task;
import com.todoapp.app.entity.User;
import com.todoapp.app.repository.Task_Repository;
import com.todoapp.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TaskResource {

    @Autowired
    Task_Repository task_repository;
    @Autowired
    UserRepository userRepository;

    @GetMapping( "/users/{id}/tasks")
    public List<Task> getAllTasks(@PathVariable int id)
    {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.get().getTasks();
    }

    @GetMapping( "/users/{id}/tasks/{tid}")
    public Task getTasks(@PathVariable int id,@PathVariable int tid)
    {
        Optional<User> userOptional = userRepository.findById(id);
        User user = userOptional.get();
        //ask_repository.
        return task_repository.findByTidAndUser(tid,user);
        //return userOptional.get();
    }

    /*
    @GetMapping( "/users/{user_id}/pending_tasks" )
    public Optional<List<Task>> get_pending_tasks(@PathVariable int id ){

        return task_repository.findByTidAndTask_status( id , "Pending" );
    }
     */

    @PostMapping( "/users/{id}/tasks" )
    public void add_task(@PathVariable int id , @RequestBody Task task ){
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent())
        {
            User user = userOptional.get();
            task.setUser(user);
            task_repository.save(task);
        }
    }

    /*
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
    */


}
