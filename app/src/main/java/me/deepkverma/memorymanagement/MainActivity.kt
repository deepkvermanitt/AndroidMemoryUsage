package me.deepkverma.memorymanagement

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import me.deepkverma.memorymanagement.logs.MemoryLogs.Companion.logCurrentMemoryProfile
import me.deepkverma.memorymanagement.service.MyService
import me.deepkverma.memorymanagement.ui.theme.MemoryManagementTheme
import me.deepkverma.memorymanagement.utils.Utils.Companion.items
import me.deepkverma.memorymanagement.utils.Utils.Companion.loadItemsInList

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logCurrentMemoryProfile("onCreate start")

        enableEdgeToEdge()
        setContent {
            MemoryManagementTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android", modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
        logCurrentMemoryProfile("onCreate after setContentView")

    }
}

private val list = arrayListOf<Int>()

@Composable
fun MainLayout(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {
                loadItemsInList("MainLayout")
                if (items.size > 100000) {
                    items.clear()
                    System.gc()
                }

            }) {
                Text(text = "MainLayout Execute ")

            }
            val context = LocalContext.current // ✅ Context le lo

            Button(onClick = {
                val intent = Intent(context, MyService::class.java)
                context.startService(intent)
            }) {
                Text(text = "Service Execute ")

            }
        }


    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    MainLayout(modifier)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MemoryManagementTheme {
        Greeting("Android")
    }
}