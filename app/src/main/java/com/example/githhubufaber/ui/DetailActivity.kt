package com.example.githhubufaber.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.githhubufaber.R
import com.example.githhubufaber.adapters.ContributorsAdapter
import com.example.githhubufaber.databinding.ActivityDetailBindingImpl
import com.example.githhubufaber.network.models.GithubModelItem
import com.example.githhubufaber.viewmodels.DetailActivityViewModel
import com.example.githhubufaber.viewmodels.HomeActivityViewModel

class DetailActivity : AppCompatActivity() {

    var contributorsAdapter: ContributorsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBindingImpl.inflate(layoutInflater)
        binding.setLifecycleOwner(this)
        val githubModelItem = intent.getParcelableExtra<GithubModelItem>("user_repo")
        val viewModelFactory =
            DetailActivityViewModel.DetailViewModelFactory(githubModelItem, application)
        val viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(DetailActivityViewModel::class.java)
        binding.viewModel = viewModel
        contributorsAdapter = ContributorsAdapter(ContributorsAdapter.OnClickListener {
            viewModel.navigateToRepositoriesDetails(it)

        })
        binding.contributorRecyclerView.adapter = contributorsAdapter
        viewModel.contributorsList.observe(this, Observer {
            contributorsAdapter!!.submitList(it)
        })
        viewModel.navigateToUsersRepositories.observe(this, Observer {
            if (null != it) {

                val intent = Intent(this,RepoActivity::class.java)
                intent.putExtra("contributor_repo",it)
                startActivity(intent)
                viewModel.navigateToRepositoriesDetailsComplete()

            }

        })

        setContentView(binding.root)
    }
}