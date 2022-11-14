package com.example.composemvicatalogo.ui.home.lifestyles

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.example.composemvicatalogo.core.models.LifeStyleListItemModel
import com.example.composemvicatalogo.core.utils.errorToast
import com.example.composemvicatalogo.core.utils.rememberLifecycleEvent

@Composable
fun LifeStyleListScreen(
    viewModel: LifeStyleViewModel,
    navController: NavController
) {
    val state: LifeStyleState by viewModel.state.collectAsState()

    val lifecycleEvent = rememberLifecycleEvent()
    if (lifecycleEvent == Lifecycle.Event.ON_RESUME) {
        LaunchedEffect(lifecycleEvent) {
            if(viewModel.listLifeStyle.isEmpty()){
                viewModel.getLifeStyles()
            }
        }
    }

    when (state) {
        is LifeStyleState.Initial -> {}
        is LifeStyleState.Error -> errorToast(
            LocalContext.current,
            (state as LifeStyleState.Error).message
        )
        is LifeStyleState.Success -> ContentList(
            (state as LifeStyleState.Success).list,
            navController
        )
        is LifeStyleState.Loading -> ContentProgress()
    }
}

@Composable
private fun ContentList(
    list: List<LifeStyleListItemModel>,
    navController: NavController
) {
    Box {
        LazyRow(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            content = {
                itemsIndexed(list) { index, item ->
                    LifeStyleItem(item = item, navController = navController)
                }
            })
    }
}

@Composable
private fun ContentProgress() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = Color.Blue
        )
    }
}