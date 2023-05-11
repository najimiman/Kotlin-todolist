package com.example.myapplication.data

class TaskDAO {
companion object {
    private var count_task=0
    private var mylist_task: MutableList<Task> = mutableListOf<Task>()
    init {
        for ( i in 1 .. 10){
            val task=Task(++count_task,"tache $i",1,System.currentTimeMillis())
            mylist_task.add(0,task)
        }
    }
}
    fun insert(taskEntry: Task){
        taskEntry.id = ++TaskDAO.count_task
        TaskDAO.mylist_task.add(0,taskEntry)
    }

    fun delete(id: Int){
        var index = this.findIndexById(id)
        mylist_task.removeAt(index)
    }

    fun update(taskEntry: Task){
        var index = this.findIndexById(taskEntry.id);
        TaskDAO.mylist_task[index] = taskEntry
    }

    private fun findIndexById(id: Int): Int {
        val index = TaskDAO.mylist_task.withIndex().filter { it.value.id == id }.map{it.index}.first()
        return index
    }


    fun getAllTasks(): MutableList<Task> {
        return TaskDAO.mylist_task
    }

    fun findById(id: Int) :Task {
        val task = TaskDAO.mylist_task.filter { it.id == id }.first()
        return task
    }
}