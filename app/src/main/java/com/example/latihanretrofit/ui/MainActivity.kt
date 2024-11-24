package com.example.latihanretrofit.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.latihanretrofit.R
import com.example.latihanretrofit.adapter.PhotoAdapter
import com.example.latihanretrofit.data.response.PhotoResponseItem
import com.example.latihanretrofit.data.retrofit.ApiConfig
import com.example.latihanretrofit.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Setup view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        ApiConfig.getApiService().getPhotos().enqueue(object : Callback<List<PhotoResponseItem>> {
            override fun onResponse(
                call: Call<List<PhotoResponseItem>>,
                response: Response<List<PhotoResponseItem>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.forEach { photo ->
                        Log.d("PhotoDebug", "Title: ${photo.title}, URL: ${photo.url}")
                    }
                    recyclerView.adapter = PhotoAdapter(response.body() ?: emptyList())
                }
            }

            override fun onFailure(call: Call<List<PhotoResponseItem>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })


    }
}