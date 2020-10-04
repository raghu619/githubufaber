package com.example.githhubufaber.viewmodels

import android.app.Application
import android.util.Log
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


class DetailActivityViewModel(
    githubModelItem: GithubModelItem,
    application: Application
) : ViewModel() {


    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _selectedProperty = MutableLiveData<GithubModelItem>()

    val selectedProperty: LiveData<GithubModelItem>
        get() = _selectedProperty


    private val _contributorsList = MutableLiveData<List<ContributorModel>>()

    val contributorsList: LiveData<List<ContributorModel>>
        get() = _contributorsList

    private val _navigateToUsersRepositories = MutableLiveData<ContributorModel>()

    val navigateToUsersRepositories: LiveData<ContributorModel>
        get() = _navigateToUsersRepositories


    init {
        _selectedProperty.value = githubModelItem
        getConRepoContributors(githubModelItem.fullName)


    }

    fun navigateToRepositoriesDetails(contributorModel: ContributorModel) {
        _navigateToUsersRepositories.value = contributorModel
    }

    fun navigateToRepositoriesDetailsComplete() {
        _navigateToUsersRepositories.value = null
    }

    private fun getConRepoContributors(username: String) {
        coroutineScope.launch {
            val getContributorListDeferred = Api.retrofitService.fetchContributorsData(
                username.split("/")[0],
                username.split("/")[1]
            )
            val listResult = getContributorListDeferred.await()
            if (listResult.size > 0) {
                _contributorsList.value = listResult
            }


        }

    }


    class DetailViewModelFactory(
        private val githubModelItem: GithubModelItem,
        private val application: Application
    ) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DetailActivityViewModel::class.java)) {
                return DetailActivityViewModel(githubModelItem, application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }


}

