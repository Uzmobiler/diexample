package uz.mobiler.diexample.retrofit

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import uz.mobiler.diexample.models.User

interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<User>
}