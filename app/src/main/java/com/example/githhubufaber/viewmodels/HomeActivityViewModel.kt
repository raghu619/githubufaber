package com.example.githhubufaber.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
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


    private val _properties = MutableLiveData<List<GithubModelItem>>()

    val properties: LiveData<List<GithubModelItem>>
        get() = _properties


    init {

        getGithubRepos()
    }

    private fun getGithubRepos() {

        coroutineScope.launch {
            var getRepoListDeferred = Api.retrofitService.fetchGithubRepos()
            var listResult = getRepoListDeferred.await()
            if (listResult.size > 0) {

                _properties.value = listResult.filter {
                    it.private == false
                }.take(20)

            }

        }

    }


}