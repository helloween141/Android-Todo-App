package com.example.todolist.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.todolist.data.Todo
import com.example.todolist.db.AppDatabase
import com.example.todolist.repositories.TodoRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

open class MainViewModel(application: Application) : AndroidViewModel(application) {
    // TODO: Implement the ViewModel
    private var todoRepository: TodoRepository = TodoRepository(application)
    private var todosLiveData: MutableLiveData<MutableList<Todo>> = todoRepository.getAllTodos()

    fun <T> MutableLiveData<T>.notifyObserver() {
        this.value = this.value
    }

    fun addTodo(todo: Todo) {
        if (todo.name.isNotEmpty()) {
            todoRepository.addTodo(todo)

            todosLiveData.value?.add(todo)
            todosLiveData.notifyObserver()
        }
    }

    fun updateTodo(todo: Todo) {
        todoRepository.updateTodo(todo)
    }

    fun deleteTodo(todo: Todo) {
        todoRepository.deleteTodo(todo)
        todosLiveData.value?.remove(todo)
        todosLiveData.notifyObserver()
    }

    fun getData() : MutableLiveData<MutableList<Todo>> = todosLiveData
}