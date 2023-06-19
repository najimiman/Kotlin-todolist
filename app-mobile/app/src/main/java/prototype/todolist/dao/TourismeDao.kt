package prototype.todolist.dao

import android.util.Log
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import prototype.todolist.dao.api.TourismeApiInterface
import prototype.todolist.models.Favorite
import prototype.todolist.models.Tourisme
import prototype.todolist.models.User
import retrofit2.Response
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

//    suspend fun save(favorite: Favorite ) = apiService.savefavorite(Favorite)

    suspend fun update(tourisme : Tourisme ) = apiService.update(tourisme.id, tourisme)
    //suspend fun login(user: User) = apiService.login(user)
    suspend fun login(email: String, password: String): Response<User> {
        val requestBody = createLoginRequestBody(email, password)
        return apiService.login(requestBody)
    }

    private fun createLoginRequestBody(email: String, password: String): RequestBody {
        val requestBodyJson = "{\"email\": \"$email\", \"password\": \"$password\"}"
//        val requestBodyJson = "{ \"token\": {\"email\": \"$email\", \"password\": \"$password\"} }"
        Log.d("fromDao", requestBodyJson.toString())
        val mediaType = "application/json".toMediaTypeOrNull()
        return requestBodyJson.toRequestBody(mediaType)
    }
    suspend fun addTofavorite(favorite: Favorite): Response<Favorite> {
        return try {
            Log.d("response favo",favorite.toString())
            apiService.savefavorite(favorite)
        } catch (e: Exception) {
            // Log the exception for debugging
            Log.e("addTofavorite", "An error occurred: ${e.message}", e)
            throw Exception("An error occurred while adding the meal to the cart: ${e.message}")
        }
    }

}