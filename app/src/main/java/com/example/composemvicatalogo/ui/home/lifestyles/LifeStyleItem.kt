package com.example.composemvicatalogo.ui.home.lifestyles

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.composemvicatalogo.core.models.LifeStyleListItemModel
import com.example.composemvicatalogo.core.utils.Constants.Companion.IMAGE_LIFE_STYLE_URL
import com.example.composemvicatalogo.ui.navigation.Arguments
import com.example.composemvicatalogo.ui.navigation.Route
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun LifeStyleItem(
    item: LifeStyleListItemModel,
    navController: NavController
) {
    val argument = "${item.link}/4/10/1" //Todo SOP
    val argumentEncoded = URLEncoder.encode(argument, StandardCharsets.UTF_8.toString())
    Column(
        modifier = Modifier.clickable {
            navController.navigate(route = "${Route.SearchLifeStyle}/${argumentEncoded}")
        },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                "${IMAGE_LIFE_STYLE_URL}${item.id}_${item.imageVersion}.png"
            ),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            text = item.title,
            textAlign = TextAlign.Center,
            style = TextStyle(
                color = Color.Black,
                fontSize = 12.sp
            )
        )
    }
}