package com.example.githhubufaber.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.githhubufaber.R
import com.example.githhubufaber.databinding.ActivityHomeBinding
import com.example.githhubufaber.viewmodels.HomeActivityViewModel

class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeActivityViewModel by lazy {
        val activity = requireNotNull(this) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProviders.of(activity).get(HomeActivityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityHomeBinding.inflate(layoutInflater)
        binding.setLifecycleOwner(this)
        viewModel.properties.observe(this, Observer {
            val list = it

        })


    }
}