package com.solid.todolistapp.view.activities.todo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solid.todolistapp.databinding.TodoListRvContentBinding
import com.solid.todolistapp.model.data.Todo


class TodoAdapter(private val selectedTodoList: List<Todo>): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(TodoListRvContentBinding
            .inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.apply {
            bind(selectedTodoList[position])
        }
    }

    override fun getItemCount() = selectedTodoList.size



    inner class TodoViewHolder(val binding: TodoListRvContentBinding):
        RecyclerView.ViewHolder(binding.root){
            fun bind(todo: Todo){
                binding.category.text = todo.category
                binding.priority.text = todo.priority
                binding.checkBox.text = todo.taskName
                binding.checkBox.isChecked = todo.finished
                binding.timeStamp.text = todo.timeStamp
            }
    }
}