package com.example.car.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.car.app.activity.CarAppActivity
import com.example.car.R
import com.example.car.ui.base.CarActivity
import com.example.car.ui.compose.ComposeExampleActivity
import com.example.car.ui.fragment.FragmentExampleActivity
import com.example.car.ui.http.HttpExampleActivity
import com.example.car.ui.music.MusicExampleActivity

class MainActivity : CarActivity(R.layout.activity_main) {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) = super
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
            findViewById<Button>(R.id.showExampleMusic)
                .setOnClickListener {
                    startActivity(
                        Intent(
                            this@MainActivity,
                            MusicExampleActivity::class.java
                        )
                    )
                }
            findViewById<Button>(R.id.showExampleCarActivity)
                .setOnClickListener {
                    startActivity(
                        Intent(
                            this@MainActivity,
                            CarAppActivity::class.java
                        )
                    )
                }
            findViewById<Button>(R.id.showExampleCompose)
                .setOnClickListener {
                    startActivity(
                        Intent(
                            this@MainActivity,
                            ComposeExampleActivity::class.java
                        )
                    )
                }

        }
}