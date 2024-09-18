package com.example.textilejobs.auth.presentation.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.textilejobs.R

@Composable
fun ThirdPartyLoginRow(modifier: Modifier = Modifier) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceEvenly) {
        IconButton(onClick = { /*TODO*/ },modifier = Modifier.size(24.dp)) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Continue With Google",
            )
        }
        IconButton(onClick = { /*TODO*/ },modifier = Modifier.size(24.dp)) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Continue With Google"
            )
        }
        IconButton(onClick = { /*TODO*/ },modifier = Modifier.size(24.dp)) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Continue With Google"
            )
        }
    }
}