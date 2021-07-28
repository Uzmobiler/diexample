package uz.mobiler.diexample.retrofit

import kotlinx.coroutines.flow.Flow
import uz.mobiler.diexample.models.User

class UserRemoteDataSource(private val apiService: ApiService) {

    suspend fun getUsers(): List<User> = apiService.getUsers()
}