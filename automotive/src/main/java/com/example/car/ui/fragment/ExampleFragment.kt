package com.example.car.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.car.R

class ExampleFragment : Fragment(R.layout.fragment_example) {

    companion object {
        fun newInstance(): Fragment = ExampleFragment()
    }

    private val viewModel: ExampleViewModel by lazy {
        ViewModelProvider(this)[ExampleViewModel::class.java]
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) = super
        .onViewCreated(view, savedInstanceState)
        .also {
            view.findViewById<TextView>(R.id.message).text = viewModel.example
        }
}