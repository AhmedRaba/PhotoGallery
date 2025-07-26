package com.silverkey.photogallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.silverkey.domain.utils.NetworkStatusTracker
import com.silverkey.photogallery.ui.theme.PhotoGalleryTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var networkStatusTracker: NetworkStatusTracker

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val isOnline by networkStatusTracker.isOnline.collectAsState()
            val systemTheme = isSystemInDarkTheme()
            var isDarkTheme by remember { mutableStateOf(systemTheme) }

            var showSplash by remember { mutableStateOf(true) }

            PhotoGalleryTheme(darkTheme = isDarkTheme) {
                if (showSplash) {
                    SplashScreen(
                        isDarkTheme = isDarkTheme,
                        onTimeout = { showSplash = false }
                    )
                } else {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text(
                                        text = "Route - ${if (isOnline) "Online" else "Offline"}",
                                        color = if (isOnline) Color.Green else Color.Red
                                    )
                                },
                                actions = {
                                    Switch(
                                        checked = isDarkTheme,
                                        onCheckedChange = { isDarkTheme = it },
                                        colors = SwitchDefaults.colors(
                                            checkedThumbColor = Color.White,
                                            uncheckedThumbColor = Color.DarkGray
                                        )
                                    )
                                }
                            )
                        }
                    ) { innerPadding ->
                        HomeScreen(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}
