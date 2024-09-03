package com.example.textilejobs.presentation.auth.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.textilejobs.R

@Composable
fun CustomButton(modifier: Modifier = Modifier, onClick: () -> Unit, text: String) {
    ElevatedButton(
        onClick = onClick, modifier = modifier, colors = ButtonDefaults.elevatedButtonColors().copy(
            containerColor = colorResource(
                id = R.color.success_green
            )
        ),
        shape = RoundedCornerShape(12)
    ) {
        Text(
            text,
            fontWeight = FontWeight.Black,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            color = Color.Black
        )
    }
}