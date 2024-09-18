package com.example.textilejobs.home.data.repository

import android.util.Log
import coil.network.HttpException
import com.example.textilejobs.core.network.ktor.HomeService
import com.example.textilejobs.core.utils.Resource
import com.example.textilejobs.home.data.dto.JobListingRequestDTO
import com.example.textilejobs.home.data.dto.JobListingResponseDTO
import com.example.textilejobs.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val homeService: HomeService) :
    HomeRepository {

    override fun getJobList(jobListingRequestDTO: JobListingRequestDTO): Flow<Resource<JobListingResponseDTO>> =
        flow {
            emit(Resource.Loading)
            try {
                val response = homeService.fetchJobs(jobListingRequestDTO)
                if (response.success == true) {
                    emit(Resource.Success(response))
                } else {
                    emit(Resource.Error(response.message ?: "Something Went Wrong"))
                }
            } catch (e: HttpException) {
                emit(Resource.Error(e.localizedMessage ?: "Something Went Wrong"))
                Log.e("HomeRepository", "HttpException occurred ${e.message}")
            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage ?: "Something Went Wrong"))
                Log.e("HomeRepository", "HttpException occurred ${e.message}")
            }
        }
}