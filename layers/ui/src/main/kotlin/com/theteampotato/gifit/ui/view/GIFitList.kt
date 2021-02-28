package com.theteampotato.gifit.ui.view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import timber.log.Timber

@Composable
fun GIFitList(items: List<String>) {
    LazyColumn {
        items(items = items) { item ->
            GIFitListElementItem(
                item,
                Modifier.padding(8.dp)
            ) { Timber.i("Clicked item $it") }
        }
    }
}