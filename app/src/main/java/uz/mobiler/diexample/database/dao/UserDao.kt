package uz.mobiler.diexample.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.mobiler.diexample.database.entity.UserEntity
import uz.mobiler.diexample.models.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(list: List<UserEntity>)

    @Query("select * from userentity")
    fun getUsers(): LiveData<List<UserEntity>>
}