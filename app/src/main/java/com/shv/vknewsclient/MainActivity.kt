package com.shv.vknewsclient

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.TextRange
import com.shv.vknewsclient.ui.theme.MainScreen
import com.shv.vknewsclient.ui.theme.VkNewsClientTheme
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAuthenticationResult
import com.vk.api.sdk.auth.VKScope

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VkNewsClientTheme {
                val someState = remember {
                    mutableStateOf(true)
                }

                Log.d("MainActivity", "Recomposition: ${someState.value}")
                val authLauncher = rememberLauncherForActivityResult(
                    contract = VK.getVKAuthActivityResultContract(),
                    onResult = {
                        when (it) {
                            is VKAuthenticationResult.Success -> {
                                Log.d("MainActivity", "Login Success")
                            }
                            is VKAuthenticationResult.Failed -> {
                                Log.d("MainActivity", "Login Failed")
                            }
                        }
                    }
                )
                LaunchedEffect(key1 = Unit) {
                    Log.d("MainActivity", "LaunchedEffect")
                }
                SideEffect {
                    Log.d("MainActivity", "SideEffect")
                }
                Button(onClick = {
                    someState.value = !someState.value
                }) {
                    Text(text = "Change state")
                }
            }
        }
    }
}
