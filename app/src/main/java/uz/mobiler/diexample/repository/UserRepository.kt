package uz.mobiler.diexample.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import uz.mobiler.diexample.database.UserLocalDataSource
import uz.mobiler.diexample.database.entity.UserEntity
import uz.mobiler.diexample.models.User
import uz.mobiler.diexample.retrofit.UserRemoteDataSource
import uz.mobiler.diexample.utils.NetworkHelper
import uz.mobiler.diexample.utils.Resource
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

class UserRepository(
    private val localDataSource: UserLocalDataSource,
    private val remoteDataSource: UserRemoteDataSource,
    private val networkHelper: NetworkHelper
) {
    private val users = MutableLiveData<Resource<List<UserEntity>>>()

    init {
        getUsers()
    }

    private fun getUsers() {
        if (networkHelper.isNetworkConnected()) {
            GlobalScope.launch {
                users.postValue(Resource.loading(null))
                try {
                    coroutineScope {
                        val async = async { remoteDataSource.getUsers() }
                        val await = async.await()
                        val allUsersFromApi = ArrayList<UserEntity>()
                        await.forEach {
                            allUsersFromApi.add(UserEntity(it.id, it.name, it.username, it.email))
                        }
                        localDataSource.addUsers(allUsersFromApi)
                        users.postValue(Resource.success(allUsersFromApi))
                    }
                } catch (e: Exception) {
                    users.postValue(Resource.error(e.toString(), null))
                }
            }
        } else {
            users.postValue(Resource.error("No internet connection", null))
        }
    }

    fun getUsersFromDb(): LiveData<Resource<List<UserEntity>>> {
        return users
    }
}