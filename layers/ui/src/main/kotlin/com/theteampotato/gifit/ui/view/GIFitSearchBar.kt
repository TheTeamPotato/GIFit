package com.theteampotato.gifit.ui.view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun GIFitSearchBar(
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    placeholderText: String = "Search..."
) {
    Row(modifier = Modifier.fillMaxHeight()) {
        val inputValue = remember {
            mutableStateOf(searchQuery)
        }

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = inputValue.value,
            onValueChange = {
                inputValue.value = it
                onSearchQueryChanged(it)
            },
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Search, contentDescription = null)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions {

            },
            /*onImeActionPerformed = { action, softKeyboardController ->
                if (action == ImeAction.Done) {
                    softKeyboardController?.hideSoftwareKeyboard()
                }
            },*/
            placeholder = { Text(text = placeholderText) },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Transparent,
                disabledTextColor = Color.Gray,
                backgroundColor = Color.White
            ),
            shape = RoundedCornerShape(20.dp)
        )
    }
}

@Composable
@Preview
fun PreviewGIFitSearchBar() {
    GIFitSearchBar(searchQuery = "Ask Me Anything", {})
}