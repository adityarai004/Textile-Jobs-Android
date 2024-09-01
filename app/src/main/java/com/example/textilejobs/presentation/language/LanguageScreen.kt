package com.example.textilejobs.presentation.language

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.textilejobs.R
import com.example.textilejobs.core.ui.TJCircularProgress
import com.example.textilejobs.presentation.language.components.LanguageGridCell
import com.example.textilejobs.presentation.language.viewmodel.LanguageEvent
import com.example.textilejobs.presentation.language.viewmodel.LanguageState
import com.example.textilejobs.presentation.language.viewmodel.LanguageViewModel


@Composable
fun LanguageRoute(languageViewModel: LanguageViewModel = viewModel(), onNavigateToLoginScreen :() -> Unit) {
    LanguagePickerScreen(modifier = Modifier.fillMaxSize(),
        languageState = languageViewModel.languageState,
        onLanguageChange = { languageViewModel.onEvent(LanguageEvent.OnSelectionChange(it)) },
        onLanguageChoose = {
            AppCompatDelegate.setApplicationLocales(
                LocaleListCompat.forLanguageTags(
                    it
                )
            )
            onNavigateToLoginScreen()
        }
    )
}

@Composable
fun LanguagePickerScreen(
    modifier: Modifier = Modifier,
    languageState: LanguageState,
    onLanguageChange: (Int) -> Unit,
    onLanguageChoose: (String) -> Unit
) {
    val languageList = languageState.languages
    Scaffold(modifier = modifier) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(25.dp))
                    Text(
                        text = stringResource(id = R.string.language_of_choice),
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 34.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    )

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.padding(10.dp)
                    ) {
                        items(languageState.languages.size) { index ->
                            LanguageGridCell(
                                languageModel = languageState.languages[index],
                                modifier = Modifier
                                    .width(150.dp)
                                    .height(80.dp)
                                    .padding(6.dp),
                                isSelected = index == languageState.selectedLanguage,
                                onClick = { onLanguageChange(index) }
                            )
                        }
                    }
                }
                ElevatedButton(
                    onClick = { onLanguageChoose(languageList[languageState.selectedLanguage].languageCode) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
                    colors = ButtonDefaults.elevatedButtonColors()
                        .copy(containerColor = colorResource(id = R.color.deep_blue)),
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Text(
                        text = "Select ${languageState.languages[languageState.selectedLanguage].language} Language",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            color = colorResource(id = R.color.white),
                            fontSize = 18.sp
                        )
                    )
                }
            }

            if (languageState.isLoading) {
                TJCircularProgress()
            }
        }

    }
}


@Preview
@Composable
private fun LanguagePrev() {
    LanguagePickerScreen(
        modifier = Modifier.fillMaxSize(),
        languageState = LanguageState(isLoading = true),
        onLanguageChange = {},
        onLanguageChoose = {},
    )
}