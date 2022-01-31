package com.example.car.ui.music

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.example.base.CarActivity
import com.example.car.R
import com.example.car.service.MusicExampleService

class MusicExampleActivity : CarActivity(R.layout.activity_example_music) {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) = super
        .onCreate(savedInstanceState)
        .also {
            findViewById<Button>(R.id.start)
                .setOnClickListener {
                    Intent(
                        this@MusicExampleActivity,
                        MusicExampleService::class.java
                    )
                        .run {
                            stopService(this)
                            startService(this)
                        }
                }
        }
}