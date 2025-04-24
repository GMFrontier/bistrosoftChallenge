package com.frommetoyou.common

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.tooling.preview.Preview

// Esta clase observa los eventos del ciclo de vida
class LifecycleEventObserver(
    private val screenName: String,
    private val logEvent: (screenName: String, event: String) -> Unit
) : DefaultLifecycleObserver {

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        logLifecycleEvent(screenName, "onCreate")
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        logLifecycleEvent(screenName, "onStart")
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        logLifecycleEvent(screenName, "onResume")
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        logLifecycleEvent(screenName, "onPause")
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        logLifecycleEvent(screenName, "onStop")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        logLifecycleEvent(screenName, "onDestroy")
    }

    private fun logLifecycleEvent(screenName: String, event: String) {
        // Aquí se puede registrar en la base de datos o hacer un log
        Log.d("LifecycleEvent", "Screen: $screenName, Event: $event")
        logEvent(screenName, event)
    }
}
