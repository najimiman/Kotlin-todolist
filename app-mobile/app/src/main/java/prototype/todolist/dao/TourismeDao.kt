package prototype.todolist.dao

import prototype.todolist.dao.api.TourismeApiInterface
import prototype.todolist.models.Tourisme
import prototype.todolist.models.User
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TourismeDao {

    companion object{

        private var BASE_URL = "http://192.168.56.1:8000/api/"

        private fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build() //Doesn't require the adapter
        }
        val apiService: TourismeApiInterface = getRetrofit().create(TourismeApiInterface::class.java)
    }

    suspend fun getTasks() = apiService.getTasks()

    suspend fun findById(id : Int ) = apiService.findById(id)

    suspend fun delete(id : Int ) = apiService.delete(id)

    suspend fun save(tourisme : Tourisme ) = apiService.save(tourisme)

    suspend fun update(tourisme : Tourisme ) = apiService.update(tourisme.id, tourisme)
    suspend fun login(user: User) = apiService.login(user)
}