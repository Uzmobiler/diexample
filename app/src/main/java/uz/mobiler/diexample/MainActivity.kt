package uz.mobiler.diexample

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import uz.mobiler.diexample.utils.Status
import uz.mobiler.diexample.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val appContainer = (application as MyApplication).appContainer
        mainViewModel = appContainer.loginViewModelFactory.create(MainViewModel::class.java)

        mainViewModel.users().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    Log.d(TAG, "onCreate: ${it.data}")
                    it.data?.forEach {
                        Log.d(TAG, "onCreate: $it")
                    }
                }
                Status.ERROR -> {

                }
                Status.LOADING -> {

                }
            }
        })
    }
}