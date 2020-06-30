package com.todoapp.app.repository;

import com.todoapp.app.entity.Task;
import com.todoapp.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Task_Repository extends JpaRepository<Task, Integer > {

    //List<Task> findByUser_Uid(int user_id );
    Task findByTidAndUser(@Param("tid") int tid, @Param("user") User user);
}
