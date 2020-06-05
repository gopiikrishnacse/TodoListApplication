package com.example.mytodoapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class TodoItem(
    @Expose
    @SerializedName("name")
    val name: String,

    @Expose
    @SerializedName("description")
    val description: String?,

    @Expose
    @SerializedName("id")
    val id: Long = 0,

    @Expose
    @SerializedName("catogory")
    val catogory: String?

)