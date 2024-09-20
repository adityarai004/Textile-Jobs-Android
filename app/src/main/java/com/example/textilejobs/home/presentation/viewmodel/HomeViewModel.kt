package com.example.textilejobs.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.textilejobs.auth.data.model.AuthResponseDTO
import com.example.textilejobs.core.utils.Resource
import com.example.textilejobs.home.data.dto.CompanyDTO
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
        _homeUiState.update { it ->
            it.copy(
                jobsList = listOf(
                    JobDTO(
                        id = 1,
                        title = "Software Engineer",
                        company = CompanyDTO(name = "ABC Tech"),
                        location = "New York, USA",
                        salary = "$90,000 - $120,000",
                        jobType = "Full-time",
                        shift = "Day",
                        postedAt = "2024-09-01",
                        description = "We are looking for a Software Engineer with experience in Kotlin and Android development."
                    ),
                    JobDTO(
                        id = 2,
                        title = "Product Manager",
                        company = CompanyDTO(name = "XYZ Corp"),
                        location = "London, UK",
                        salary = "£70,000 - £100,000",
                        jobType = "Full-time",
                        shift = "Night",
                        postedAt = "2024-08-25",
                        description = "Lead the product development team and manage project timelines for financial software products."
                    ),
                    JobDTO(
                        id = 3,
                        title = "Data Scientist",
                        company = CompanyDTO(name = "LMN Inc"),
                        location = "Remote",
                        salary = "$110,000 - $150,000",
                        jobType = "Part-time",
                        shift = "General",
                        postedAt = "2024-09-10",
                        description = "Analyze large datasets to derive actionable insights for healthcare applications."
                    ),
                    JobDTO(
                        id = 3,
                        title = "Data Scientist",
                        company = CompanyDTO(name = "LMN Inc"),
                        location = "Remote",
                        salary = "$110,000 - $150,000",
                        jobType = "Part-time",
                        shift = "General",
                        postedAt = "2024-09-10",
                        description = "Analyze large datasets to derive actionable insights for healthcare applications."
                    ),
                    JobDTO(
                        id = 3,
                        title = "Data Scientist",
                        company = CompanyDTO(name = "LMN Inc"),
                        location = "Remote",
                        salary = "$110,000 - $150,000",
                        jobType = "Part-time",
                        shift = "General",
                        postedAt = "2024-09-10",
                        description = "Analyze large datasets to derive actionable insights for healthcare applications."
                    ),
                    JobDTO(
                        id = 3,
                        title = "Data Scientist",
                        company = CompanyDTO(name = "LMN Inc"),
                        location = "Remote",
                        salary = "$110,000 - $150,000",
                        jobType = "Part-time",
                        shift = "General",
                        postedAt = "2024-09-10",
                        description = "Analyze large datasets to derive actionable insights for healthcare applications."
                    ),
                    JobDTO(
                        id = 3,
                        title = "Data Scientist",
                        company = CompanyDTO(name = "LMN Inc"),
                        location = "Remote",
                        salary = "$110,000 - $150,000",
                        jobType = "Part-time",
                        shift = "General",
                        postedAt = "2024-09-10",
                        description = "Analyze large datasets to derive actionable insights for healthcare applications."
                    ),
                    JobDTO(
                        id = 3,
                        title = "Data Scientist",
                        company = CompanyDTO(name = "LMN Inc"),
                        location = "Remote",
                        salary = "$110,000 - $150,000",
                        jobType = "Part-time",
                        shift = "General",
                        postedAt = "2024-09-10",
                        description = "Analyze large datasets to derive actionable insights for healthcare applications."
                    ),
                    JobDTO(
                        id = 3,
                        title = "Data Scientist",
                        company = CompanyDTO(name = "LMN Inc"),
                        location = "Remote",
                        salary = "$110,000 - $150,000",
                        jobType = "Part-time",
                        shift = "General",
                        postedAt = "2024-09-10",
                        description = "Analyze large datasets to derive actionable insights for healthcare applications."
                    )
                )
            )

        }
//        viewModelScope.launch(Dispatchers.IO) {
//            getJobListingUseCase(_homeUiState.value.currentPage, 10).collect {
//                handleJobsData(it)
//            }
//        }
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