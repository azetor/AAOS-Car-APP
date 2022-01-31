package com.example.car.ui.http

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.base.CarActivity
import com.example.car.R
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HttpExampleActivity : CarActivity(R.layout.activity_example_http) {

    private val scope by lazy { CoroutineScope(Dispatchers.IO) }
    private val client by lazy { HttpClient() }

    override fun onCreate(
        savedInstanceState: Bundle?
    ) = super
        .onCreate(savedInstanceState)
        .apply {
            request()
        }

    private fun request() = scope
        .launch {
            val response = client.request<String> {
                url("https://api.github.com/users")
                header("Accept", "application/vnd.github.v3+json")
                method = HttpMethod.Get
            }
            withContext(Dispatchers.Main) {
                findViewById<TextView>(R.id.users).text = response
                Toast.makeText(this@HttpExampleActivity, "SUCCESS", Toast.LENGTH_SHORT).show()
            }
        }
}