package com.example.protodo

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter (
    private val tasks: MutableList<Task>,
    private val onEdit: (Int)-> Unit,
    private val onDelete: (Int)-> Unit
    ): RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.findViewById<TextView>(R.id.textViewTitle)
        val edit = view.findViewById<Button>(R.id.buttonEdit)
        val delete = view.findViewById<Button>(R.id.buttonDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun getItemCount() = tasks.size
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.title.text = tasks[position].title
        holder.edit.setOnClickListener { onEdit(position) }
        holder.edit.setOnClickListener { onDelete(position) }
    }
}






