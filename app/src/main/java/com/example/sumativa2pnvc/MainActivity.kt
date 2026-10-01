package com.example.sumativa2pnvc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.sumativa2pnvc.ui.screens.PropinaScreen
import com.example.sumativa2pnvc.ui.theme.Sumativa2PNVCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Sumativa2PNVCTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PropinaScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
