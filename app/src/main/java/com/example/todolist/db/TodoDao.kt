package com.example.todolist.db

import androidx.room.*
import com.example.todolist.data.Todo

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo")
    fun getAll(): MutableList<Todo>

    @Insert
    fun insertAll(vararg todos: Todo)

    @Update
    fun update(vararg todos: Todo)

    @Delete
    fun delete(todo: Todo)
}