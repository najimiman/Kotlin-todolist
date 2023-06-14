package prototype.todolist.dao.api

import okhttp3.RequestBody
import prototype.todolist.models.Tourisme
import prototype.todolist.models.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface TourismeApiInterface {

    @GET("cityplace")
    suspend fun getTasks(): List<Tourisme>

    @GET("edit/{id}")
    suspend fun findById(@Path("id") id : Int) : Tourisme

    @DELETE("destroy/{id}")
    suspend fun delete(@Path("id") id : Int) : Int

    @POST("addfavorite/")
    suspend fun save(@Body tourisme : Tourisme) : Tourisme

    @PATCH("edit/{id}")
    suspend fun update(@Path("id") id : Int, @Body tourisme : Tourisme) : Tourisme
    @POST("login")
    suspend fun login(@Body requestBody: RequestBody): Response<User>
}
