package com.example.myapplication.data

class TaskReposotorie() {
    private val taskDao = TaskDAO()
    private fun insert(taskEntry: Task) = taskDao.insert(taskEntry)
    private fun update(taskEntry: Task) = taskDao.update(taskEntry)
    fun delete(taskId: Int) = taskDao.delete(taskId)
    fun getAllTasks() = taskDao.getAllTasks()
    fun findById(id: Int) = taskDao.findById(id)
    fun newTask(): Task {
        return Task(0,"",0,System.currentTimeMillis())
    }

    fun save(taskEntry: Task) {

        if(taskEntry.id == 0){
            this.insert(taskEntry)
        }else{
            this.update(taskEntry)
        }
    }
}