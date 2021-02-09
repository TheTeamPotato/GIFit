package com.theteampotato.gifit.ui.view

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GIFitList(items: List<String>) {
    LazyColumnFor(items = items) { item ->
        GIFitListElementItem(
            item,
            Modifier.padding(8.dp)
        ) { Log.i("ListClick", "Clicked item $it") }
    }
}