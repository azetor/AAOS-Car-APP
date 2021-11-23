package com.example.car.ui.screen

import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.*

class ExampleScreen(carContext: CarContext) : Screen(carContext) {

    override fun onGetTemplate(): Template = PaneTemplate
        .Builder(
            Pane
                .Builder()
                .addRow(
                    Row
                        .Builder()
                        .setTitle("Hello Car!")
                        .build()
                )
                .build()
        )
        .setHeaderAction(Action.APP_ICON)
        .build()
}