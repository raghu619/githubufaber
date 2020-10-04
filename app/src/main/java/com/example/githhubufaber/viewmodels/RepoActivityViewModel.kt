package com.example.githhubufaber.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githhubufaber.network.Api
import com.example.githhubufaber.network.models.ContributorModel
import com.example.githhubufaber.network.models.GithubModelItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RepoActivityViewModel(
    contributorModel: ContributorModel,
    application: Application
) : ViewModel() {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _selectedProperty = MutableLiveData<ContributorModel>()

    val selectedProperty: LiveData<ContributorModel>
        get() = _selectedProperty


    private val _repositoriesList = MutableLiveData<List<GithubModelItem>>()

    val repositoriesList: LiveData<List<GithubModelItem>>
        get() = _repositoriesList

    init {
        _selectedProperty.value = contributorModel
        getUserRepositories(contributorModel.name)

    }

    private fun getUserRepositories(name: String) {
        coroutineScope.launch {
            val getRepositoriesDeffered = Api.retrofitService.fetchUserRepos(name)
            val listResult = getRepositoriesDeffered.await()
            if (listResult.size > 0) {
                _repositoriesList.value = listResult
            }
        }

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