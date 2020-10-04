package com.example.githhubufaber.ui

import android.content.Intent
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
        ViewModelProviders.of(this, HomeActivityViewModel.Factory(activity.application))
            .get(HomeActivityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityHomeBinding.inflate(layoutInflater)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel
        binding.reposRecyclerView.adapter = GithubRepoAdapter(GithubRepoAdapter.OnClickListener {
            viewModel.displayPropertyDetails(it)

        })
        viewModel.navigateToSelectedProperty.observe(this, Observer {
            if (null != it) {

                val intent = Intent(this@HomeActivity, DetailActivity::class.java)
                intent.putExtra("user_repo", it)
                startActivity(intent)
                viewModel.displayPropertyDetailsComplete()

            }
        })
        setContentView(binding.root)
    }
}