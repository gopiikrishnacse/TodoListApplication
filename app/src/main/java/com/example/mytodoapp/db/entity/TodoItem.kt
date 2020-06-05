package com.example.mytodoapp.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = "todo")
data class TodoItem(

    @PrimaryKey(autoGenerate = true)
    @NotNull
    val id: Long=0,

    @ColumnInfo(name = "name")
    @NotNull
    val name: String,

    @ColumnInfo(name = "description")
    @NotNull
    val description: String,

    @ColumnInfo(name = "category")
    @NotNull
    val category: String
) : Serializable