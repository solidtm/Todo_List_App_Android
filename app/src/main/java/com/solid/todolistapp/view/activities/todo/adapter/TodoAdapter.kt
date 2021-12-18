package com.solid.todolistapp.view.activities.todo.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.solid.todolistapp.R
import com.solid.todolistapp.databinding.TodoListRvContentBinding
import com.solid.todolistapp.model.Todo
import com.solid.todolistapp.view.fragments.HomeFragmentDirections


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
                if(priorityCardColor == R.color.checked){
                    binding.priorityCardView.setCardBackgroundColor(Color.parseColor("#16DB65"))
                }
                if (priorityCardColor == R.color.category){
                    binding.priorityCardView.setCardBackgroundColor(Color.parseColor("#ffda85"))
                }
                if (priorityCardColor == R.color.category_color){
                    binding.priorityCardView.setCardBackgroundColor(Color.parseColor("#ba1b1d"))
                }

                if (categoryCardColor == R.color.category){
                    binding.categoryCardView.setCardBackgroundColor(Color.parseColor("#ffda85"))
                }
                if (categoryCardColor == R.color.family_background){
                    binding.categoryCardView.setCardBackgroundColor(Color.parseColor("#ade8f4"))
                }
                if (categoryCardColor == R.color.school){
                    binding.categoryCardView.setCardBackgroundColor(Color.parseColor("#ffa7c5"))
                }
            }
    }
}