package com.example.protodo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.GeneratedAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var taskAdapter: TaskAdapter
    private val tasks = mutableListOf<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.editTextTask)
        val buttonAdd = findViewById<Button>(R.id.buttonAdd)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        taskAdapter = TaskAdapter(tasks,
            onEdit = { position ->
                val task = tasks[position]
                val input = EditText(this)
                input.setText(task.title)
                AlertDialog.Builder(this)
                    .setTitle("Edit Task")
                    .setView(input)
                    .setPositiveButton("Save") { _, _ ->
                        tasks[position].title = input.text.toString()
                        taskAdapter.notifyItemChanged(position)
                    }
                    .setNegativeButton("Cancel", null)
                    .show()
            },
            onDelete = { position ->
                tasks.removeAt(position)
                taskAdapter.notifyItemRemoved(position)
            }
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = taskAdapter

        buttonAdd.setOnClickListener {
            val taskTitle = editText.text.toString()
            if (taskTitle.isNotBlank()) {
                tasks.add(Task(taskTitle))
                taskAdapter.notifyItemInserted(tasks.size - 1)
                editText.text.clear()
            }
        }
    }
}
