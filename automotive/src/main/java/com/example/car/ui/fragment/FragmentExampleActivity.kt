package com.example.car.ui.fragment

import android.os.Bundle
import com.example.base.CarActivity
import com.example.car.R

class FragmentExampleActivity : CarActivity(R.layout.activity_example_fragment) {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) = super
        .onCreate(savedInstanceState)
        .also {
            if (savedInstanceState == null) {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, ExampleFragment.newInstance())
                    .commitNow()
            }
        }
}