package com.shv.vknewsclient

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
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
                authLauncher.launch(arrayListOf(VKScope.WALL))
                MainScreen()
            }
        }
    }
}
