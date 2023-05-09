package prototype.todolist

import prototype.todolist.data.TaskDao
import prototype.todolist.data.TaskEntry
import prototype.todolist.data.TaskRepository

fun main (){

    val taskRepository = TaskRepository();

    // Ajouter une tâche
    val task = TaskEntry(0,"Task 103",1,System.currentTimeMillis());
//    taskRepository.save(task);

    // Suprimer une tâche
//    taskRepository.delete(11)
    // Modifier une tâche
    val task1 = TaskEntry(1,"Task 111",1,System.currentTimeMillis());
    taskRepository.save(task1)
//    for (item in taskRepository.getAllTasks()){
//        println(item)
//    }
    // Afficher les tâches
    for (item in taskRepository.getAllTasks()){
        println(item)
    }

}