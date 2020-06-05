package com.example.mytodoapp.db

import com.example.mytodoapp.db.entity.TodoItem
import com.mindorks.bootcamp.instagram.data.local.db.DatabaseService

interface DbHelper {

    suspend fun getTodoItems(): List<TodoItem>

    suspend fun insertTodoItem(item: TodoItem)

}

class DbHelperImpl(private val database: DatabaseService) : DbHelper{
    override suspend fun getTodoItems(): List<TodoItem> = database.todoDao().getAll()

    override suspend fun insertTodoItem(item: TodoItem) = database.todoDao().insert(item)
}