package com.example.githhubufaber.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.githhubufaber.adapters.GithubRepoAdapter
import com.example.githhubufaber.databinding.ActivityRepoBinding
import com.example.githhubufaber.network.models.ContributorModel
import com.example.githhubufaber.viewmodels.RepoActivityViewModel

class RepoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRepoBinding.inflate(layoutInflater)
        binding.setLifecycleOwner(this)
        val contributorModel = intent.getParcelableExtra<ContributorModel>("contributor_repo")
        val viewModelFactory =
            RepoActivityViewModel.RepoViewModelFactory(contributorModel, application)
        val viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(RepoActivityViewModel::class.java)
        binding.viewModel = viewModel
        binding.userRepoRecyclerView.adapter=  GithubRepoAdapter(GithubRepoAdapter.OnClickListener {


        })

        setContentView(binding.root)
    }
}