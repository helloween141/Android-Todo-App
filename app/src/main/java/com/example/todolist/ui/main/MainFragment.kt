package com.example.todolist.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.adapters.TodoAdapter
import com.example.todolist.data.Todo
import com.example.todolist.databinding.MainFragmentBinding

class MainFragment : Fragment()  {
    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: MainFragmentBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider.AndroidViewModelFactory(activity!!.application)
            .create(MainViewModel::class.java)

        val todoView = binding.todoText
        binding.addTodoBtn.setOnClickListener {
            val todoText = todoView.text
            val todo = Todo(todoText.toString(), false)
            viewModel.addTodo(todo)
            todoText.clear()
        }

        val adapter = TodoAdapter(viewModel)
        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(view?.context)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(view?.context, DividerItemDecoration.VERTICAL))

        viewModel.getData().observe(this, Observer {
            adapter.refresh(it)
        })
    }


}