package uz.mobiler.diexample.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.mobiler.diexample.database.entity.UserEntity
import uz.mobiler.diexample.models.User
import uz.mobiler.diexample.repository.UserRepository
import uz.mobiler.diexample.utils.NetworkHelper
import uz.mobiler.diexample.utils.Resource

class MainViewModel(
    private val repository: UserRepository
) : ViewModel() {

    fun users(): LiveData<Resource<List<UserEntity>>> {
        return repository.getUsersFromDb()
    }
}