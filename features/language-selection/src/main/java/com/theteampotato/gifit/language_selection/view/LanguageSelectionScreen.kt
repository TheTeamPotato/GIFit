package com.theteampotato.gifit.language_selection.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel

import com.airbnb.lottie.compose.*

import com.theteampotato.gifit.language_selection.R
import com.theteampotato.gifit.language_selection.viewmodel.LanguageSelectionViewModel
import com.theteampotato.gifit.ui.babyBlue
import com.theteampotato.gifit.ui.view.GIFitLanguageCard

import timber.log.Timber

@Composable
fun LanguageSelectionScreen(modifier: Modifier = Modifier, navigateToSearch: () -> Unit = {}, viewModel: LanguageSelectionViewModel = hiltViewModel()) {
    val supportedLanguageList = remember { mutableStateOf(getSupportedLanguages().sortedBy { it.name }) }

    val selectedIndex = remember { mutableStateOf(-1) }
    val isContinueSelected = remember { mutableStateOf(false) }

    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {

        val (title, container, button, snackbar) = createRefs()
        val bottomHorizontalAnchor = createGuidelineFromBottom(0.10f)

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 32.dp)
                .constrainAs(title) {
                    top.linkTo(parent.top)
                },
            text = "Select a source language \n" + "to translate from",
            textAlign = TextAlign.Center,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        LazyColumn(modifier = Modifier
            .constrainAs(container) {
                top.linkTo(title.bottom)

                if (selectedIndex.value != -1)
                    bottom.linkTo(button.top, 24.dp)
                else
                    bottom.linkTo(parent.bottom)

                height = Dimension.fillToConstraints
            }) {
            itemsIndexed(supportedLanguageList.value) { index, supportedLanguage ->
                supportedLanguage.apply {
                    GIFitLanguageCard(text = name, painter = painterResource(iconResourceId), isSelected = selectedIndex.value == index, onClicked = {
                        selectedIndex.value = index
                    })
                }
            }
        }

        if (selectedIndex.value != -1) {
            Column(
                modifier = Modifier
                    .constrainAs(button) {
                        top.linkTo(bottomHorizontalAnchor)
                    }, verticalArrangement = Arrangement.Center
            ) {
                Button(modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(horizontal = 25.dp),
                    enabled = !isContinueSelected.value,
                    colors = ButtonDefaults.buttonColors(backgroundColor = babyBlue),
                    shape = MaterialTheme.shapes.medium,
                    onClick = {
                        isContinueSelected.value = true

                        viewModel.downloadTranslationModel(
                            sourceLanguageCode = supportedLanguageList.value[selectedIndex.value].languageCode,
                            onSuccess = {
                                Timber.d("onSuccess()")
                                isContinueSelected.value = false
                                navigateToSearch()
                            },
                            onFailure = {
                                Timber.d("onFailure()")
                                isContinueSelected.value = false
                            }
                        )
                    }) {
                    Text(fontWeight = FontWeight.Bold, text = "Continue", color = Color.White)
                }
            }
        }
    }

    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("spinner.json"))

    if (isContinueSelected.value) {
        Surface(modifier = Modifier.fillMaxSize(), color = Color.Gray.copy(alpha = 0.5f)) {
            LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever, alignment = Alignment.Center)
            Snackbar(modifier = Modifier
                .wrapContentSize(align = Alignment.BottomCenter)
                .padding(start = 8.dp, end = 8.dp, bottom = 20.dp)
            ) { Text(text = "Please wait while downloading translation model!") }
        }
    }
}

data class SupportedLanguage(val name: String, val iconResourceId: Int, val languageCode: String)

enum class Language(val languageCode: String) {
    CHINESE("zh"),
    DANISH("da"),
    FRENCH("fr"),
    GERMAN("de"),
    SPANISH("es"),
    TURKISH("tr"),
    ITALIAN("it"),
    JAPANESE("ja"),
    DUTCH("nl"),
    NORWEGIAN("no"),
    KOREAN("ko"),
    SWEDISH("sv")
}

private fun getSupportedLanguages() = listOf(
    SupportedLanguage(name = "Chinese", iconResourceId = R.drawable.ic_china, languageCode = Language.CHINESE.languageCode),
    SupportedLanguage(name = "Danish", iconResourceId = R.drawable.ic_denmark, languageCode = Language.DANISH.languageCode),
    SupportedLanguage(name = "French", iconResourceId = R.drawable.ic_france, languageCode = Language.FRENCH.languageCode),
    SupportedLanguage(name = "German", iconResourceId = R.drawable.ic_germany, languageCode = Language.GERMAN.languageCode),
    SupportedLanguage(name = "Spanish", iconResourceId = R.drawable.ic_spain, languageCode = Language.SPANISH.languageCode),
    SupportedLanguage(name = "Turkish", iconResourceId = R.drawable.ic_turkey, languageCode = Language.TURKISH.languageCode),
    SupportedLanguage(name = "Italian", iconResourceId = R.drawable.ic_italy, languageCode = Language.ITALIAN.languageCode),
    SupportedLanguage(name = "Japanese", iconResourceId = R.drawable.ic_japan, languageCode = Language.JAPANESE.languageCode),
    SupportedLanguage(name = "Dutch", iconResourceId = R.drawable.ic_netherlands, languageCode = Language.DUTCH.languageCode),
    SupportedLanguage(name = "Norwegian", iconResourceId = R.drawable.ic_norway, languageCode = Language.NORWEGIAN.languageCode),
    SupportedLanguage(name = "Korean", iconResourceId = R.drawable.ic_south_korea, languageCode = Language.KOREAN.languageCode),
    SupportedLanguage(name = "Swedish", iconResourceId = R.drawable.ic_sweden, languageCode = Language.SWEDISH.languageCode)
)