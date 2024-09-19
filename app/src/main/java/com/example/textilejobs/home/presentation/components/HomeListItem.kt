package com.example.textilejobs.home.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.textilejobs.R
import com.example.textilejobs.home.data.dto.CompanyDTO
import com.example.textilejobs.home.data.dto.JobDTO

@Composable
fun HomeListItem(modifier: Modifier = Modifier, job: JobDTO) {
    Column(
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .border(BorderStroke(1.dp, Color.Black), shape = RoundedCornerShape(20.dp))
            .height(180.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(6.dp)
                .height(40.dp)
        ) {
            AsyncImage(
                model = job.description, contentDescription = null, placeholder = painterResource(
                    id = R.drawable.ic_google,
                ),
                modifier = Modifier.size(40.dp)
            )
            Column(
                modifier
                    .fillMaxWidth(0.8f)
                    .fillMaxHeight()
                    .padding(start = 20.dp),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = job.title ?: "Some Job",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                )
                Text(
                    text = job.company?.name ?: "Some Corp",
                    style = TextStyle(
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        color = Color.Black
                    )
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = null)
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(6.dp)) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = null,
                modifier = Modifier.size(26.dp),
            )
            Text(
                text = job.location ?: "World",
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    color = Color.Black
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeListItemPrev() {
    HomeListItem(
        job = JobDTO(
            id = 2,
            title = "Product Manager",
            company = CompanyDTO(name = "XYZ Corp"),
            location = "London, UK",
            salary = "£70,000 - £100,000",
            jobType = "Full-time",
            isRemote = false,
            postedAt = "2024-08-25",
            description = "Lead the product development team and manage project timelines for financial software products."
        )
    )
}