package com.example.simplegetrequest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var tv: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv = findViewById(R.id.tv)
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        if (apiInterface != null) {
            apiInterface.getName()?.enqueue(object : Callback<Data?> {
                override fun onResponse(call: Call<Data?>, response: Response<Data?>) {
                    val res = response.body()!!
                    var text = ""
                    for (name in res) {
                        text += name.name + "\n"
                    }
                    tv.text = text
                }

                override fun onFailure(call: Call<Data?>, t: Throwable) {
                    Toast.makeText(applicationContext, "${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }


}