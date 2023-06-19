package prototype.todolist.repositoryies


import android.util.Log
import prototype.todolist.dao.TourismeDao
import prototype.todolist.models.Favorite
import prototype.todolist.models.Tourisme
import prototype.todolist.models.User

class TourismeRepository () {

    private val tourismeDao = TourismeDao()

    suspend fun getTasks() = tourismeDao.getTasks()

    suspend fun findById(id : Int) = tourismeDao.findById(id)

    suspend fun delete(id : Int) = tourismeDao.delete(id)

    suspend fun save(favorite: Favorite){
        if(favorite.id == 0){
            // save
            tourismeDao.addTofavorite(favorite)
        }
//        else{
//            // update
//            tourismeDao.update(tourisme = Tourisme())
//        }
//
   }
//    suspend fun login(user: User) = tourismeDao.login(email = String(), password = String())
}