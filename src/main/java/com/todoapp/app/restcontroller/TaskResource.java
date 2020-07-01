package com.todoapp.app.restcontroller;

import com.todoapp.app.entity.Task;
import com.todoapp.app.entity.User;
import com.todoapp.app.repository.Task_Repository;
import com.todoapp.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Transactional
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

    @DeleteMapping( "/users/{user_id}/tasks/{task_id}" )
    public void delete_task( @PathVariable int user_id , @PathVariable int task_id ){
        Optional<User> userOptional = userRepository.findById( user_id );
        User user = userOptional.get();
        task_repository.deleteByTidAndUser( task_id , user );
    }


    @PutMapping( "/users/{user_id}/tasks/{task_id}" )
    public void update_task( @PathVariable int user_id , @PathVariable int task_id , @RequestBody Task task ){

        Task old_details = getTasks( user_id , task_id );
        task.setTid( old_details.getTid() );
        task.setUser( old_details.getUser() );
        task.setTask_creation_date( old_details.getTask_creation_date() );
        task.setTask_status( old_details.getTask_status() );

        if( task.getDeadline() == null ){

            task.setDeadline( old_details.getDeadline() );
        }

        if( task.getTask_description() == null ){

            task.setTask_description( old_details.getTask_description() );
        }

        if( task.getTask_name() == null ){

            task.setTask_name( old_details.getTask_name() );
        }

        task_repository.save( task );
    }

}
