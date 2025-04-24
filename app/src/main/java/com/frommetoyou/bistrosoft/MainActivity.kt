package com.frommetoyou.bistrosoft

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.compose.rememberNavController
import com.frommetoyou.bistrosoft.ui.navigation.AppBottomNavigation
import com.frommetoyou.bistrosoft.ui.navigation.CentralNavigation
import com.frommetoyou.bistrosoft.ui.theme.BistrosoftTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BistrosoftTheme {
                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        AppBottomNavigation(navController = navController)
                    }
                ) { innerPadding ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                start = innerPadding.calculateStartPadding(
                                    LayoutDirection.Rtl
                                ),
                                end = innerPadding.calculateEndPadding(
                                    LayoutDirection.Rtl
                                ),
                                bottom = innerPadding.calculateBottomPadding(),
                                top = innerPadding.calculateTopPadding()
                            )
                    ) {

                        CentralNavigation(navController = navController)
                    }

                }
            }
        }
    }
}