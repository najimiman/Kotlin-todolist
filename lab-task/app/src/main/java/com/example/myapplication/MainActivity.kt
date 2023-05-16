package com.example.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.myapplication.data.TaskReposotorie
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ui.TaskAdapter
//import androidx.recyclerview.widget.LinearLayoutManager
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {

            val taskAdapter = TaskAdapter()
            receycleview.layoutManager = LinearLayoutManager(applicationContext)
            receycleview.adapter =  taskAdapter
            floatingActionButton2.setOnClickListener{

                val repository = TaskReposotorie()
                val newTask = repository.newTask();
//                newTask.title = "New task" + Random.nextInt()
                newTask.title = "New task" + Random.nextInt()
                repository.save(newTask)

                Toast.makeText(applicationContext,"Ajouter une tâche", Toast.LENGTH_LONG).show()
                taskAdapter.notifyDataSetChanged()
            }

        }
    }
}