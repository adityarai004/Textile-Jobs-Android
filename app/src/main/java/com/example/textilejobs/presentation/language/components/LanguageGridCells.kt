package com.example.textilejobs.presentation.language.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.textilejobs.R
import com.example.textilejobs.presentation.language.viewmodel.LanguageModel

@Composable
fun LanguageGridCell(
    modifier: Modifier = Modifier,
    languageModel: LanguageModel,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    OutlinedCard(
        onClick = onClick,
        modifier = modifier,
        border = if (isSelected) BorderStroke(1.5.dp, Color.Cyan) else BorderStroke(
            1.5.dp,
            Color.Black
        ),
        elevation = CardDefaults.outlinedCardElevation(defaultElevation = 5.dp),
        colors = CardDefaults.outlinedCardColors().copy(containerColor = Color.White),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = languageModel.language,
                color = Color.Black,
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
            )
            Image(
                painter = painterResource(id = languageModel.refImage),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                alpha = 0.5f
            )
        }
    }
}

@Preview
@Composable
private fun LanguageGridPrev() {
    LanguageGridCell(
        languageModel = LanguageModel("English", "en", R.drawable.statue_of_librery),
        modifier = Modifier
            .width(150.dp)
            .height(80.dp),
        isSelected = true,
        onClick = {}
    )
}