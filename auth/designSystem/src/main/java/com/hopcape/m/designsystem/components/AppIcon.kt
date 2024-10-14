package com.hopcape.m.designsystem.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hopcape.m.designsystem.R

@Composable
fun AppIcon(
    size: Dp = 100.dp,
    resId: Int = R.drawable.app_logo
) {
    Image(
        modifier = Modifier.size(size),
        painter = painterResource(id = resId),
        contentDescription = "Image"
    )
}