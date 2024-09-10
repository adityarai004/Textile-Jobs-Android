package com.example.textilejobs.core.desginsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.textilejobs.R

@Composable
fun TJCircularProgress() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.muted_gray).copy(alpha = 0.6f))
            .pointerInput(Unit) {},
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .height(100.dp)
                .width(100.dp)
                .background(
                    colorResource(id = R.color.sky_blue).copy(alpha = 0.8f),
                    shape = RoundedCornerShape(14.dp)
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircularProgressIndicator(color = colorResource(id = R.color.white))
        }
    }
}