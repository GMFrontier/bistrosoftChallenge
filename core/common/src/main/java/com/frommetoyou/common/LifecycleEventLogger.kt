package com.frommetoyou.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.lifecycle.compose.LocalLifecycleOwner

@Composable
fun LifecycleEventLogger(screenName: String, logEvent: (screenName: String, event: String) -> Unit) {
    val lifecycleOwner = LocalLifecycleOwner.current

    // Registrar el observer del ciclo de vida
    DisposableEffect(lifecycleOwner) {
        val lifecycleObserver = LifecycleEventObserver(screenName, logEvent)
        lifecycleOwner.lifecycle.addObserver(lifecycleObserver)

        // Eliminar el observer cuando el Composable se desmonte
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(lifecycleObserver)
        }
    }
}
