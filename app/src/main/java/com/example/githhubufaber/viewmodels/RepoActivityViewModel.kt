package com.example.githhubufaber.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githhubufaber.network.Api
import com.example.githhubufaber.network.models.ContributorModel
import com.example.githhubufaber.network.models.GithubModelItem
import com.example.githhubufaber.showTostMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RepoActivityViewModel(
    contributorModel: ContributorModel,
    application: Application
) : ViewModel() {

    lateinit var app: Application
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _selectedProperty = MutableLiveData<ContributorModel>()

    val selectedProperty: LiveData<ContributorModel>
        get() = _selectedProperty


    private val _repositoriesList = MutableLiveData<List<GithubModelItem>>()

    val repositoriesList: LiveData<List<GithubModelItem>>
        get() = _repositoriesList

    private val _navigateToSelectedProperty = MutableLiveData<GithubModelItem>()

    val navigateToSelectedProperty: LiveData<GithubModelItem>
        get() = _navigateToSelectedProperty


    init {
        app = application
        _selectedProperty.value = contributorModel
        getUserRepositories(contributorModel.name)

    }

    private fun getUserRepositories(name: String) {
        coroutineScope.launch {
            val getRepositoriesDeffered = Api.retrofitService.fetchUserRepos(name)
            val listResult = getRepositoriesDeffered.await()
            try {


                if (listResult.size > 0) {
                    _repositoriesList.value = listResult
                }
            }catch (t:Throwable){

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


    class RepoViewModelFactory(
        private val contributorModel: ContributorModel,
        private val application: Application
    ) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(RepoActivityViewModel::class.java)) {
                return RepoActivityViewModel(contributorModel, application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }


}