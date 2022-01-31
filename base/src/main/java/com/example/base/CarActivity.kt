package com.example.base

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

private const val NO_LAYOUT_RES = -1

open class CarActivity(
    @LayoutRes val layoutResID: Int = NO_LAYOUT_RES
) : AppCompatActivity() {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) = super
        .onCreate(savedInstanceState)
        .also {
            if (layoutResID != NO_LAYOUT_RES) {
                setContentView(layoutResID)
            }
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