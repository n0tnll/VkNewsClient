package com.shv.vknewsclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.shv.vknewsclient.ui.theme.ActivityResultTest
import com.shv.vknewsclient.ui.theme.VkNewsClientTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VkNewsClientTheme {
                ActivityResultTest()
            }
        }
    }
}
