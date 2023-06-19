package prototype.todolist.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import prototype.todolist.models.Tourisme
import prototype.todolist.models.User
import prototype.todolist.repositoryies.TourismeRepository
import prototype.todolist.utils.Resource

class TourismeViewModel : ViewModel()  {

    private val tourismeRepository = TourismeRepository()

    fun getTasks() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = tourismeRepository.getTasks()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun findById(id : Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = tourismeRepository.findById(id)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun delete(id : Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = tourismeRepository.delete(id)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

//    fun save(tourisme: Tourisme) = liveData(Dispatchers.IO) {
//        emit(Resource.loading(data = null))
//        try {
//            emit(Resource.success(data = tourismeRepository.save(tourisme)))
//        } catch (exception: Exception) {
//            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
//        }
//    }
//    fun login(user: User) = liveData(Dispatchers.IO) {
//        emit(Resource.loading(data=null))
//        try {
//            emit(Resource.success(data=tourismeRepository.login(user)))
//        }
//        catch (exception: Exception) {
//            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
//        }
//    }

}