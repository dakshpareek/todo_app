package com.todoapp.app.repository;

import com.todoapp.app.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Task_Repository extends JpaRepository<Task, Integer > {

    List<Task> findByUser_Uid(int user_id );

    Optional<List<Task>> findByTidAndTask_status(int user_id , String task_status );

}
