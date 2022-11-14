package com.example.composemvicatalogo.ui.home.lifestyles.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import com.example.composemvicatalogo.core.models.SearchLifeStyleItemModel
import com.example.composemvicatalogo.core.utils.errorToast
import com.example.composemvicatalogo.core.utils.rememberLifecycleEvent

@Composable
fun SearchLifeStyleScreen(
    viewModel: SearchLifeStyleViewModel
) {
    val lifecycleEvent = rememberLifecycleEvent()
    if (lifecycleEvent == Lifecycle.Event.ON_RESUME) {
        LaunchedEffect(lifecycleEvent) {
            viewModel.getSearchLifeStyles()
        }
    }

    val state : SearchLifeStyleState by viewModel.state.collectAsState()
    when (state) {
        is SearchLifeStyleState.Initial -> {}
        is SearchLifeStyleState.Error -> errorToast(LocalContext.current, (state as SearchLifeStyleState.Error).message)
        is SearchLifeStyleState.Success -> ContentList((state as SearchLifeStyleState.Success).list)
        is SearchLifeStyleState.Loading -> {}
    }
}

@Composable
private fun ContentList(
    list: List<SearchLifeStyleItemModel>,
) {
    Box {
        LazyColumn(
            content = {
                itemsIndexed(list) { index, item ->
                    SearchLifeStyleItem(item = item)
                }
            })
    }
}