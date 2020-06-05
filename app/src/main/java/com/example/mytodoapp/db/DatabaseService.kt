package com.mindorks.bootcamp.instagram.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mytodoapp.db.dao.TodoDao
import com.example.mytodoapp.db.entity.TodoItem

@Database(
    entities = [
        TodoItem::class
    ],
    exportSchema = false,
    version = 1
)
abstract class DatabaseService : RoomDatabase() {

    abstract fun todoDao(): TodoDao
}