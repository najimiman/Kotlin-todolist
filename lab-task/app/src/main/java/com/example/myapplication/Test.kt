package com.example.myapplication

import com.example.myapplication.data.Task
import com.example.myapplication.data.TaskReposotorie

fun main (){

    val taskRepository = TaskReposotorie();

    // Ajouter une tâche
    val task = Task(0,"Task 103",1,System.currentTimeMillis());
//    taskRepository.save(task);

    // Suprimer une tâche
//taskRepository.delete(11)
    // Modifier une tâche
//    val task1 = Task(1,"Task 111",1,System.currentTimeMillis());
//    taskRepository.save(task1)
    // Afficher les tâches
    for (item in taskRepository.getAllTasks()){
        println(item)
    }

}