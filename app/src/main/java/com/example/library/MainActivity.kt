package com.example.library

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.library.presentation.photos.ui.NavGraphs
import com.example.library.ui.theme.Compose_appTheme
import com.ramcosta.composedestinations.DestinationsNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose_appTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}




