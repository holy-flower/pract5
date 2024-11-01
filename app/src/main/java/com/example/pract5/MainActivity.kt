package com.example.pract5

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var userRV: RecyclerView
    lateinit var loadingDB: ProgressBar
    lateinit var userAdapter: UserAdapter
    lateinit var userList: ArrayList<User>
    lateinit var db: AppDatabase
    lateinit var buttonNext: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userRV = findViewById(R.id.idRVUsers)
        loadingDB = findViewById(R.id.idPBUsers)
        buttonNext = findViewById(R.id.buttonNext)
        db = AppDatabase.getDatabase(this)

        userList = ArrayList()

        clearDatabase()
        getAllUsers()

        buttonNext.setOnClickListener {
            val intent = Intent(this, DatabaseActivity::class.java)
            startActivity(intent)
        }

    }

    private fun getAllUsers() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/c/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitAPI = retrofit.create(RetrofitAPI::class.java)

        val call: Call<ArrayList<User>> = retrofitAPI.getAllUsers()

        // Отправка асинхронного запроса к API.
        call.enqueue(object : Callback<ArrayList<User>> {
            override fun onResponse(
                call: Call<ArrayList<User>>,
                response: Response<ArrayList<User>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    loadingDB.visibility = View.GONE
                    userList = response.body()!!

                    lifecycleScope.launch(Dispatchers.IO) {
                        db.userDao().insertUsers(userList)
                    }

                    userAdapter = UserAdapter(userList)
                    userRV.adapter = userAdapter
                } else {
                    Toast.makeText(this@MainActivity, "No data found", Toast.LENGTH_SHORT).show()
                }
            }


            override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Fail to get the data..", Toast.LENGTH_SHORT)
                    .show()
                t.printStackTrace()
            }
        })

    }

    private fun clearDatabase() {
        lifecycleScope.launch(Dispatchers.IO) {
            db.userDao().deleteAllUsers()
        }
    }
}