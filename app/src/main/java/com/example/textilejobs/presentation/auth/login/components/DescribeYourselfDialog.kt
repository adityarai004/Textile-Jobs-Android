package com.example.textilejobs.presentation.auth.login.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.textilejobs.R
import com.example.textilejobs.presentation.auth.login.state.DialogState


@Composable
fun DescribeYourselfDialog(
    dialogState: DialogState,
    onDismiss: () -> Unit,
    onSelectionChange: (Int) -> Unit,
    onSubmit: () -> Unit
) {
    val userType = dialogState.userType
    if (dialogState.showDialog) {
        Dialog(onDismissRequest = onDismiss) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.describe_yourself),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp),
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp
                    ),
                )
                userType.forEach {
                    val color =
                        if (userType[dialogState.selectedUserType] == it) Color.Green.copy(
                            alpha = 0.6f
                        ) else Color.Gray
                    Row(modifier = Modifier
                            .clickable(interactionSource = null, indication = null){
                                onSelectionChange(userType.indexOf(it))
                            }
                            .fillMaxWidth()
                            .padding(6.dp)
                            .border(
                                2.dp, color = color,
                                RoundedCornerShape(12.dp)
                            )
                            .padding(vertical = 16.dp, horizontal = 6.dp),
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.CheckCircle,
                                contentDescription = null,
                                tint = color
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(text = stringResource(id = it))
                        }
                    }
                }
                ElevatedButton(
                    onClick = onSubmit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp),
                    colors = ButtonDefaults.elevatedButtonColors().copy(
                        containerColor = colorResource(
                            id = R.color.muted_gray
                        )
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.submit),
                        style = TextStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.Black,
                            fontSize = 16.sp
                        )
                    )
                }
            }
        }
    }

}
