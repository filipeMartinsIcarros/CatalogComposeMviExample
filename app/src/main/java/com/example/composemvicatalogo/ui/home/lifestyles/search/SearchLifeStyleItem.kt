package com.example.composemvicatalogo.ui.home.lifestyles.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.composemvicatalogo.core.models.SearchLifeStyleItemModel

@Composable
fun SearchLifeStyleItem(
    item: SearchLifeStyleItemModel,
) {
    Column(
        modifier = Modifier.clickable {  },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = item.make,
            textAlign = TextAlign.Center,
            style = TextStyle(
                color = Color.Black,
                fontSize = 12.sp
            )
        )
    }
}