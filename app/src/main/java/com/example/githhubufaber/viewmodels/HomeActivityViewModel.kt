package com.example.githhubufaber.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githhubufaber.network.Api
import com.example.githhubufaber.network.models.GithubModelItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class HomeActivityViewModel : ViewModel() {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    private val _navigateToSelectedProperty = MutableLiveData<GithubModelItem>()

    val navigateToSelectedProperty: LiveData<GithubModelItem>
        get() = _navigateToSelectedProperty



    private val _properties = MutableLiveData<List<GithubModelItem>>()

    val properties: LiveData<List<GithubModelItem>>
        get() = _properties


    init {

        getGithubRepos()
    }

    private fun getGithubRepos() {

        coroutineScope.launch {
            val getRepoListDeferred = Api.retrofitService.fetchGithubRepos()
            val listResult = getRepoListDeferred.await()
            if (listResult.size > 0) {

                _properties.value = listResult.filter {
                    it.private == false
                }.take(20)

            }

        }

    }

    fun displayPropertyDetails(githubModelItem: GithubModelItem) {
        _navigateToSelectedProperty.value = githubModelItem
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }


}