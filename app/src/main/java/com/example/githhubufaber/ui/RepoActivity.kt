package com.example.githhubufaber.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
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
        binding.userRepoRecyclerView.adapter = GithubRepoAdapter(GithubRepoAdapter.OnClickListener {
            viewModel.displayPropertyDetails(it)

        })
        viewModel.navigateToSelectedProperty.observe(this, Observer {
            if (null != it) {

                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("user_repo", it)
                startActivity(intent)
                viewModel.displayPropertyDetailsComplete()

            }
        })

        setContentView(binding.root)
    }
}