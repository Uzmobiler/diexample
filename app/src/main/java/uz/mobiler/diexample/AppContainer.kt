package uz.mobiler.diexample

import android.content.Context
import uz.mobiler.diexample.database.AppDatabase
import uz.mobiler.diexample.database.UserLocalDataSource
import uz.mobiler.diexample.repository.UserRepository
import uz.mobiler.diexample.retrofit.ApiClient
import uz.mobiler.diexample.retrofit.UserRemoteDataSource
import uz.mobiler.diexample.utils.NetworkHelper
import uz.mobiler.diexample.viewmodels.ViewModelFactory

class AppContainer(context: Context) {

    val remoteDataSource = UserRemoteDataSource(ApiClient.apiService)
    val localDataSource = UserLocalDataSource(AppDatabase.getInstance(context).userDao())
    val networkHelper = NetworkHelper(context)
    var userRepository = UserRepository(localDataSource, remoteDataSource, networkHelper)
    var loginViewModelFactory = ViewModelFactory(userRepository)

}