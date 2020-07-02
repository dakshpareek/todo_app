package com.todoapp.app.restcontroller;

import com.todoapp.app.entity.Task;
import com.todoapp.app.entity.User;
import com.todoapp.app.repository.Task_Repository;
import com.todoapp.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import javax.transaction.Transactional;

@Transactional
@RestController
public class TaskResource extends RepresentationModel {

    @Autowired
    Task_Repository task_repository;
    @Autowired
    UserRepository userRepository;

    @Value("${Config.limit}")
    int page_limit;
    //calling /users/1/tasks?offset=0
    @GetMapping( "/users/{id}/tasks")
    public CollectionModel getAllTasks(@PathVariable int id,@RequestParam(name = "offset",defaultValue = "0") int page_number,
                                  @RequestParam(name="completed",defaultValue = "-1") int isCompleted)
    {
        Optional<User> userOptional = userRepository.findById(id);
        User user = userOptional.get();

        //Resources <user > resources = new Resources < > (collection);

        //Getting page number through parameter and getting fixed size results
        Pageable firstPage = PageRequest.of(page_number, page_limit);

        List<Task> taskList;

        //by default show both type of tasks of a user
        if(isCompleted == -1) {
            taskList = task_repository.findAllByUser(user, firstPage);
        }
        //show pending tasks only of a user
        else if (isCompleted == 0) {
            taskList = task_repository.findAllByUserAndTaskStatus(user, false, firstPage);
        }
        else
        {
            taskList = task_repository.findAllByUserAndTaskStatus(user, true, firstPage);
        }


        List<EntityModel<Task>> taskEntityModel = new ArrayList<>();

        for(Task task : taskList)
        {
            EntityModel<Task> entityModel = EntityModel.of(task);
            Link link = linkTo(methodOn(TaskResource.class).getTasks(id,task.getTid())).withSelfRel();
            entityModel.add(link);
            taskEntityModel.add(entityModel);
        }
        //System.out.println(taskEntityModel);

        CollectionModel collectionModel = CollectionModel.of(taskEntityModel);
        //EntityModel listEntityModel = EntityModel.of(taskEntityModel);
        // /users/{id}/tasks
        Link nextPage = linkTo(methodOn(TaskResource.class).getAllTasks(id,page_number + 1,isCompleted)).withRel("Next Page");
        Link prevPage = linkTo(methodOn(TaskResource.class).getAllTasks(id,page_number - 1,isCompleted)).withRel("Previous Page");

        collectionModel.add(nextPage);
        collectionModel.add(prevPage);

        return collectionModel;
    }



    @GetMapping( "/users/{id}/tasks/{tid}")
    public EntityModel<Task> getTasks(@PathVariable int id, @PathVariable int tid)
    {
        Optional<User> userOptional = userRepository.findById(id);
        User user = userOptional.get();
        //ask_repository.
        Task task = task_repository.findByTidAndUser(tid, user);
        //Task task = task_repository.findByTidAndUser_Uid(tid, id);
        EntityModel<Task> taskEntityModel = EntityModel.of(task);

        Link link = linkTo(methodOn(TaskResource.class).getTasks(id,tid)).withSelfRel();
        Link link2 = linkTo(methodOn(TaskResource.class).getAllTasks(id,0,0)).withRel("All Pending Tasks");
        Link link3 = linkTo(methodOn(TaskResource.class).getAllTasks(id,0,1)).withRel("All Completed Tasks");
        //Link link3 = linkTo(TaskResource.class).slash("users").slash(id).slash("tasks").withRel("addresses");

        taskEntityModel.add(link);
        taskEntityModel.add(link2);
        taskEntityModel.add(link3);

        return taskEntityModel;
        //return userOptional.get();
    }


    @PatchMapping( "/users/{id}/tasks/{tid}")
    public void updateTaskStatus(@PathVariable int id,@PathVariable int tid,@RequestBody Task partialUpdate)
    {
        Optional<User> userOptional = userRepository.findById(id);
        User user = userOptional.get();

        if (userOptional.isPresent())
        {
            Task task = task_repository.findByTidAndUser(tid, user);
            task.setTaskStatus(partialUpdate.getTaskStatus());
            task_repository.save(task);
        }
    }


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

        Optional<User> userOptional = userRepository.findById( user_id );
        User user = userOptional.get();

        //Task old_details = getTasks( user_id , task_id );
        Task old_details = task_repository.findByTidAndUser(task_id,user);
        task.setTid( old_details.getTid() );
        task.setUser( old_details.getUser() );
        task.setTask_creation_date( old_details.getTask_creation_date() );
        task.setTaskStatus(old_details.getTaskStatus());

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
