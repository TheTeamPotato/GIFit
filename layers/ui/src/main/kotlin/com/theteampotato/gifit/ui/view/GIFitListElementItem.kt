package com.theteampotato.gifit.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GIFitListElementItem(
    listElementText: String,
    modifier: Modifier = Modifier,
    itemOnClick: (String) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Card(
            elevation = 8.dp,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .width(320.dp)
                .height(70.dp)
                .clickable {
                    itemOnClick(listElementText)
                }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = listElementText, fontSize = 24.sp)
            }
        }
    }
}

@Preview
@Composable
fun PreviewGIFitListElementItem() =
    GIFitListElementItem("Ask Me Anything", Modifier.padding(8.dp)) {}