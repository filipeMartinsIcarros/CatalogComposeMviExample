package com.example.composemvicatalogo.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.composemvicatalogo.ui.home.lifestyles.LifeStyleViewModel
import com.example.composemvicatalogo.ui.home.lifestyles.search.SearchLifeStyleViewModel
import com.example.composemvicatalogo.ui.theme.AppTheme
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.module

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(module)
        setContent {
            AppTheme {
                AppScreens()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(module)
    }

    companion object {
        val module = module {
            viewModel { LifeStyleViewModel(get()) }
            viewModel { SearchLifeStyleViewModel(get(), get()) }
        }
    }
}
