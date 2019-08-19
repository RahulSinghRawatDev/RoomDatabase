package com.example.mainactivity.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.mainactivity.model.Task;

import java.util.List;

/*********************************************************
 * @author Rahul Singh Rawat
 * We have created TaskDao for accessing #Task Table value.
 * function getAllTask() for accessing all the Task Table rows values.
 * function insertTask() for inserting a single object values.
 * function deleteTask() for deleting particular value in the database
 * function UpdateTask() for updating particular value in the database
 ***********************************************************/


@Dao
public interface TaskDao {

    @Query("SELECT * FROM task ORDER BY id DESC")
    List<Task> getAllTask();

    @Insert
    void insertTask(Task task);

    @Delete
    void deleteTask(Task task);

    @Update
    void updateTask(Task task);

}
