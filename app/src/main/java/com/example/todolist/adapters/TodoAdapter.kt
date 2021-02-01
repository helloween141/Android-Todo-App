package com.example.todolist.adapters

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.data.Todo
import com.example.todolist.ui.main.MainViewModel

class TodoAdapter(val viewModel: MainViewModel) : RecyclerView.Adapter<TodoAdapter.ViewHolder>() {
    private var todos: MutableList<Todo> = mutableListOf()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nameView: TextView = view.findViewById(R.id.name)
        var checkedView: CheckBox = view.findViewById(R.id.checked)
        var deleteView: ImageView = view.findViewById(R.id.delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.todo_item,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    fun refresh(todos: MutableList<Todo>) {
        this.todos = todos
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(nameView: TextView, isChecked: Boolean) {
        if(isChecked) {
            nameView.paintFlags = nameView.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            nameView.paintFlags = nameView.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentTodo = todos[position]

        holder.apply {
            nameView.text = currentTodo.name
            checkedView.isChecked = currentTodo.checked
            toggleStrikeThrough(nameView, currentTodo.checked)

            checkedView.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(nameView, isChecked)
                currentTodo.checked = !currentTodo.checked
                viewModel.updateTodo(currentTodo)
            }

            deleteView.setOnClickListener{_ ->
                viewModel.deleteTodo(currentTodo)
            }
        }
    }

}