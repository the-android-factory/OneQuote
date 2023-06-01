package com.androidfactory.onequote

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

@Composable
fun PerformOnLifecycle(
    lifecycleOwner: LifecycleOwner,
    onStart: () -> Unit = { },
    onResume: () -> Unit = { }
) {
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_START -> onStart()
                Lifecycle.Event.ON_RESUME -> onResume()
                else -> Log.i("OBSERVER", "Lifecycle: ${event.name}")
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)
        return@DisposableEffect onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}