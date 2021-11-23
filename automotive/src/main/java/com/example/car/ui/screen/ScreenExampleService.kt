package com.example.car.ui.screen

import android.content.Intent
import android.content.pm.ApplicationInfo
import androidx.car.app.CarAppService
import androidx.car.app.R
import androidx.car.app.Screen
import androidx.car.app.Session
import androidx.car.app.validation.HostValidator

class ScreenExampleService : CarAppService() {

    override fun createHostValidator(): HostValidator = when {
        applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0 -> HostValidator.ALLOW_ALL_HOSTS_VALIDATOR
        else -> HostValidator.Builder(applicationContext)
            .addAllowedHosts(R.array.hosts_allowlist_sample)
            .build()
    }

    override fun onCreateSession(): Session = object : Session() {
        override fun onCreateScreen(
            intent: Intent
        ): Screen = ExampleScreen(carContext)
    }
}