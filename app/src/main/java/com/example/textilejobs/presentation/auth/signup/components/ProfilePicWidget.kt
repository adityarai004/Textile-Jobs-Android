package com.example.textilejobs.presentation.auth.signup.components

import android.net.Uri
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.textilejobs.R

@Composable
fun ImagePicker(modifier: Modifier = Modifier, filePath: Uri?, onClickUpload: () -> Unit) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Box {
            AsyncImage(
                model = filePath,
                contentDescription = "Your Picked Image",
                modifier = Modifier
                    .size(200.dp)
                    .clip(shape = CircleShape)
                    .clipToBounds(),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.ic_launcher_background),
                error = painterResource(id = R.drawable.ic_launcher_background)
            )
            IconButton(
                onClick = onClickUpload, modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Edit,
                    contentDescription = "Upload Image",
                    modifier = Modifier
                        .size(32.dp)
                        .border(width = 1.dp, color = Color.Black, shape = CircleShape)
                        .padding(3.dp),
                    tint = Color.Black
                )
            }
        }
    }
}
