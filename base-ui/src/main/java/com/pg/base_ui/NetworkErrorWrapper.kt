package com.pg.base_ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun NetworkErrorWrapper(content: @Composable () -> Unit) {
    var connection by remember {
        mutableStateOf(Network.Connection.AVAILABLE)
    }

    LaunchedEffect(key1 = Unit) {
        launch {
            Network.networkConnection.collectLatest { newConnection ->
                connection = newConnection
            }
        }
    }

    Column {
        AnimatedVisibility(
            visible = connection == Network.Connection.LOST,
            enter = slideInVertically(),
            exit = slideOutVertically()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .background(color = Color.Red),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Network Error")
            }
        }
        content()
    }
}