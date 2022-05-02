package com.diwixis.filmlibrary.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.diwixis.filmlibrary.R

@Composable
fun ShowMoreItem(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
            .clickable { onClick() }
            .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        Image(painter = painterResource(id = R.drawable.ic_arrow), contentDescription = null)
    }
}