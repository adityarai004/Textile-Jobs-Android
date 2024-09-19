package com.example.textilejobs.home.presentation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.textilejobs.home.data.dto.CompanyDTO
import com.example.textilejobs.home.data.dto.JobDTO
import com.example.textilejobs.home.presentation.viewmodel.HomeViewModel

@Composable
fun HomeRoute(modifier: Modifier = Modifier, homeViewModel: HomeViewModel = viewModel()) {
    val homeUiState = homeViewModel.homeUiState.collectAsStateWithLifecycle()
    val jobsList = homeUiState.value.jobsList

    HomeScreen(jobsList = jobsList)
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier, jobsList: List<JobDTO>) {
    LazyColumn {

    }
}

@Preview
@Composable
private fun HomeScreenComp() {
    HomeScreen(
        jobsList = listOf(
            JobDTO(
                id = 1,
                title = "Software Engineer",
                company = CompanyDTO(name = "ABC Tech"),
                location = "New York, USA",
                salary = "$90,000 - $120,000",
                jobType = "Full-time",
                isRemote = true,
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
                isRemote = false,
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
                isRemote = true,
                postedAt = "2024-09-10",
                description = "Analyze large datasets to derive actionable insights for healthcare applications."
            )
        )
    )
}