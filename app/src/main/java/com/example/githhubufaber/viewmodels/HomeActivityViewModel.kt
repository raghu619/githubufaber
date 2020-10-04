package com.example.githhubufaber.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.githhubufaber.network.Api
import com.example.githhubufaber.network.models.GithubModelItem
import com.example.githhubufaber.showTostMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class HomeActivityViewModel(application: Application) : AndroidViewModel(application) {

    lateinit var app: Application
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    private val _navigateToSelectedProperty = MutableLiveData<GithubModelItem>()

    val navigateToSelectedProperty: LiveData<GithubModelItem>
        get() = _navigateToSelectedProperty


    private val _properties = MutableLiveData<List<GithubModelItem>>()

    val properties: LiveData<List<GithubModelItem>>
        get() = _properties


    init {

        app = application
        getGithubRepos()
    }
    // fetches top github repositories list
    private fun getGithubRepos() {

        coroutineScope.launch {

            try {


                val getRepoListDeferred = Api.retrofitService.fetchGithubRepos()
                val listResult = getRepoListDeferred.await()
                if (listResult.size > 0) {

                    _properties.value = listResult.filter {
                        it.private == false
                    }.take(20)

                }

            } catch (t: Throwable) {


                showTostMessage(app.applicationContext)

            }

        }

    }

    fun displayPropertyDetails(githubModelItem: GithubModelItem) {
        _navigateToSelectedProperty.value = githubModelItem
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }


    class Factory(val app: Application) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeActivityViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return HomeActivityViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }


}