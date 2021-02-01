package com.example.todolist.repositories

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.todolist.data.Todo
import com.example.todolist.db.AppDatabase
import com.example.todolist.db.TodoDao
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TodoRepository(application: Application) {
    private var todoDao: TodoDao
    private var allTodos: MutableLiveData<MutableList<Todo>> = MutableLiveData()

    init {
        val db = AppDatabase.getInstance(application.applicationContext)!!
        todoDao = db.todoDao()

        GlobalScope.launch {
            allTodos.postValue(todoDao.getAll())
        }
    }

    fun addTodo(todo: Todo) {
        GlobalScope.launch {
            todoDao.insertAll(todo)
        }
    }

    fun updateTodo(todo: Todo) {
        GlobalScope.launch {
            todoDao.update(todo)
        }
    }

    fun deleteTodo(todo: Todo) {
        GlobalScope.launch {
            todoDao.delete(todo)
        }
    }

    fun getAllTodos() = allTodos
}