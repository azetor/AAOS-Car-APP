package com.example.car.ui.fragment

import android.os.Bundle
import com.example.car.R
import com.example.car.ui.base.CarActivity

class FragmentExampleActivity : CarActivity(R.layout.activity_fragment_example) {

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