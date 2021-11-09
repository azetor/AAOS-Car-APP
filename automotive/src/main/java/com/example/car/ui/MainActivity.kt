package com.example.car.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.example.car.R
import com.example.car.ui.base.CarActivity
import com.example.car.ui.fragment.FragmentExampleActivity
import com.example.car.ui.http.HttpExampleActivity

class MainActivity : CarActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) = super
        .onCreate(savedInstanceState)
        .also {
            findViewById<Button>(R.id.showExampleFragment)
                .setOnClickListener {
                    startActivity(
                        Intent(
                            this@MainActivity,
                            FragmentExampleActivity::class.java
                        )
                    )
                }
            findViewById<Button>(R.id.showExampleHttp)
                .setOnClickListener {
                    startActivity(
                        Intent(
                            this@MainActivity,
                            HttpExampleActivity::class.java
                        )
                    )
                }
        }
}