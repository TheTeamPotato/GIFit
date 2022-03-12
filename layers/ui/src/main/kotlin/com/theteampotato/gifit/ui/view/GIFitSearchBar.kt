package com.theteampotato.gifit.ui.view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.theteampotato.gifit.ui.hideKeyboard

@Composable
fun GIFitSearchBar(
    modifier: Modifier = Modifier,
    text: String,
    onSearchQueryChanged: (String) -> Unit = {},
    onSearchQueryEntered: () -> Unit = {},
    placeholderText: String = "Search..."
) {
    val context = LocalContext.current

    Row(modifier = modifier) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = {
                onSearchQueryChanged(it)
            },
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Search, contentDescription = null)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search,
            ),
            keyboardActions = KeyboardActions {
                onSearchQueryEntered()
                hideKeyboard(context)
            },
            placeholder = { Text(text = placeholderText) },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                cursorColor = Color.Black,
                disabledTextColor = Color.Gray,
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            singleLine = true,
            shape = RoundedCornerShape(20.dp)
        )
    }
}

@Composable
@Preview
fun PreviewGIFitSearchBar() {
    GIFitSearchBar(text = "Ask Me Anything", onSearchQueryChanged = {})
}