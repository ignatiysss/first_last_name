package com.example.myapplication

import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(
    var todos: List<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_todo,
            parent, false
        )

        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.itemView.apply {
            findViewById<EditText>(R.id.textViewTodo).text = Editable.Factory.getInstance().newEditable(todos[position].title)
            findViewById<CheckBox>(R.id.checkboxDone).isChecked = todos[position].isChecked
        }
    }

    override fun getItemCount(): Int = todos.size
}