package com.example.githhubufaber.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githhubufaber.R
import com.example.githhubufaber.adapters.GithubRepoAdapter
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
        binding.viewModel = viewModel
        binding.reposRecyclerView.adapter = GithubRepoAdapter()
        setContentView(binding.root)
    }
}