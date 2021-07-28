package uz.mobiler.diexample.database

import androidx.lifecycle.LiveData
import uz.mobiler.diexample.database.dao.UserDao
import uz.mobiler.diexample.database.entity.UserEntity
import uz.mobiler.diexample.models.User

class UserLocalDataSource(private val userDao: UserDao) {

    suspend fun addUsers(list: List<UserEntity>) {
        userDao.insertUsers(list)
    }

    fun getUsers(): LiveData<List<UserEntity>> {
        return userDao.getUsers()
    }
}