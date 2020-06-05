package com.example.mytodoapp.db

import android.content.Context
import androidx.room.Room
import com.mindorks.bootcamp.instagram.data.local.db.DatabaseService

object DatabaseBuilder {

    private var INSTANCE: DatabaseService? = null

    fun getInstance(context: Context): DatabaseService {
        if (INSTANCE == null) {
            synchronized(DatabaseService::class) {
                INSTANCE =  Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseService::class.java,
                    "todo_db"
                ).build()
            }
        }
        return INSTANCE!!
    }



}