package com.example.textilejobs.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.textilejobs.auth.data.model.AuthResponseDTO
import com.example.textilejobs.core.utils.Resource
import com.example.textilejobs.home.data.dto.JobDTO
import com.example.textilejobs.home.data.dto.JobListingResponseDTO
import com.example.textilejobs.home.domain.usecase.GetJobListingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getJobListingUseCase: GetJobListingUseCase) :
    ViewModel() {
    private val _homeUiState = MutableStateFlow(HomeUIState())
    val homeUiState = _homeUiState
        .onStart { fetchJobs() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            HomeUIState()
        )

    private fun fetchJobs() {
        viewModelScope.launch(Dispatchers.IO) {
            getJobListingUseCase.invoke(_homeUiState.value.currentPage, 10).collect {
                handleJobsData(it)
            }
        }
    }

    private fun handleJobsData(resource: Resource<JobListingResponseDTO>) {
        when (resource) {
            is Resource.Error -> {
                _homeUiState.update {
                    it.copy(
                        loadingJobs = false,
                        homeError = true,
                        homeErrorString = resource.message,
                    )

                }
            }

            is Resource.Loading -> {
                _homeUiState.update {
                    it.copy(
                        loadingJobs = true
                    )
                }
            }

            is Resource.Success -> {
                if (resource.data.success == true) {
                    _homeUiState.update {
                        it.copy(
                            loadingJobs = false,
                            homeError = false,
                            homeErrorString = "",
                            jobsList = resource.data.jobData?.jobs ?: emptyList()
                        )
                    }
                } else {
                    _homeUiState.update {
                        it.copy(
                            loadingJobs = false,
                            homeError = true,
                            homeErrorString = resource.data.message ?: "Something Went Wrong",
                        )
                    }
                }
            }
        }
    }

}