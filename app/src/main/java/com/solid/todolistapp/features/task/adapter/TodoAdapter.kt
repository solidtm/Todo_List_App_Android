package com.solid.todolistapp.features.task.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.solid.todolistapp.R
import com.solid.todolistapp.databinding.TodoListRvContentBinding
import com.solid.todolistapp.data.model.Todo
import com.solid.todolistapp.features.home.HomeFragmentDirections


class TodoAdapter: RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    private var selectedTodoList = emptyList<Todo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(TodoListRvContentBinding
            .inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.apply {
            bind(selectedTodoList[position])
        }

        val currentItem = selectedTodoList[position]
        holder.binding.rowLayout.setOnClickListener{
            val action = HomeFragmentDirections.actionNavigationHomeToNavigationDashboard(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount() = selectedTodoList.size

    fun setData(todos: List<Todo>){
        this.selectedTodoList = todos
        notifyDataSetChanged()
    }



    inner class TodoViewHolder(val binding: TodoListRvContentBinding):
        RecyclerView.ViewHolder(binding.root){
            fun bind(todo: Todo){
                binding.category.text = todo.category
                binding.priority.text = todo.priority
                binding.checkBox.text = todo.taskName
                binding.checkBox.isChecked = todo.finished
                binding.timeStamp.text = todo.timeStamp

                val priorityCardColor = todo.priorityCardColor
                val categoryCardColor = todo.categoryCardColor

                binding.priorityCardView.setCardBackgroundColor(ContextCompat.getColor(binding.priorityCardView.context, priorityCardColor))
                binding.categoryCardView.setCardBackgroundColor(ContextCompat.getColor(binding.categoryCardView.context, categoryCardColor))
            }
    }
}