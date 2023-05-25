package com.example.myapplication.ui

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.navigation.NavController

import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.TaskRepository

class TaskAdapter (navController:NavController):RecyclerView.Adapter<TaskAdapter.TaskViewHolder>(){
    private val taskRepository=TaskRepository()
    private val navController=navController
    class TaskViewHolder (private  val view: View):RecyclerView.ViewHolder(view){
        val taskTitle:TextView=view.findViewById<Button>(androidx.appcompat.R.id.tas)
        val taskRepository:TextView=view.findViewById<Button>(androidx.appcompat.R.id.tas)

    }

}