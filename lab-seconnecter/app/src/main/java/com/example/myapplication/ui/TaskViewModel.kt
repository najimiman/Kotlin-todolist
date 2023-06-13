package com.example.myapplication.ui

//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.liveData
//import com.example.myapplication.models.Task
//import com.example.myapplication.repositories.TasksRepository
//import com.example.myapplication.utils.Resource
//import kotlinx.coroutines.Dispatchers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.myapplication.repositories.TasksRepository
import kotlinx.coroutines.Dispatchers
import com.example.myapplication.models.Task
import com.example.myapplication.utils.Resource

class TaskViewModel : ViewModel() {
    private val tasksRepository = TasksRepository()

    fun getTasks() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = tasksRepository.getTasks()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun findById(id : Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = tasksRepository.findById(id)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun delete(id : Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = tasksRepository.delete(id)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun save(task : Task) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = tasksRepository.save(task)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}