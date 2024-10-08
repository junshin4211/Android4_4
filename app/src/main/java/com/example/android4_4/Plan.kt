package com.example.android4_4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.android4_4.ui.theme.Android4_4Theme
import com.example.android4_4.ui.theme.Purple40

class Plan :ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Android4_4Theme {
                Test()
            }
        }
    }

}

@Composable
fun Test(){
    Column (
        modifier = Modifier.fillMaxSize()
            .background(color = Purple40)
    ){
        Text(text = "Hello")
    }
}

@Preview(showBackground = true)
@Composable
fun TestPreview() {
    Android4_4Theme {
        Test()
    }
}