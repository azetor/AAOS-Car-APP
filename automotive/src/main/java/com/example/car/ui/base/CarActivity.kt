package com.example.car.ui.base

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

open class CarActivity(
    @LayoutRes val layoutResID: Int
) : AppCompatActivity() {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) = super
        .onCreate(savedInstanceState)
        .also {
            setContentView(layoutResID)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

    override fun onOptionsItemSelected(
        item: MenuItem
    ): Boolean = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}