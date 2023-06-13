package prototype.todolist.repositoryies


import prototype.todolist.dao.TourismeDao
import prototype.todolist.models.Tourisme
import prototype.todolist.models.User

class TourismeRepository () {

    private val tourismeDao = TourismeDao()

    suspend fun getTasks() = tourismeDao.getTasks()

    suspend fun findById(id : Int) = tourismeDao.findById(id)

    suspend fun delete(id : Int) = tourismeDao.delete(id)

    suspend fun save(tourisme : Tourisme){
        if(tourisme.id == 0){
            // save
            tourismeDao.save(tourisme)
        }else{
            // update
            tourismeDao.update(tourisme)
        }

    }
    suspend fun login(user: User) = tourismeDao.login(user)
}