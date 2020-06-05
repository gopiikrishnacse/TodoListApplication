package com.example.mytodoapp.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.mytodoapp.db.entity.TodoItem


@Dao
interface TodoDao {

    @Query("SELECT * FROM todo")
    fun getAll(): List<TodoItem>

    @Insert
    fun insert(entity: TodoItem)

    @Delete
    fun delete(entity: TodoItem)
}