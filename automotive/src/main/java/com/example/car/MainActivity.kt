package com.example.car

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : Activity() {

    private val scope by lazy { CoroutineScope(Dispatchers.IO) }
    private val client by lazy { HttpClient() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        request()
    }

    private fun request() {
        scope
            .launch {
                val response = client.request<String> {
                    url("https://api.github.com/users")
                    header("Accept", "application/vnd.github.v3+json")
                    method = HttpMethod.Get
                }
                withContext(Dispatchers.Main) {
                    findViewById<TextView>(R.id.users).text = response
                    Toast.makeText(this@MainActivity, "SUCCESS", Toast.LENGTH_SHORT).show()
                }
            }
    }
}