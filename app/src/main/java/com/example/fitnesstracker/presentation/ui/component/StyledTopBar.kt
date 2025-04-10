package com.example.fitnesstracker.presentation.ui.component

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import com.example.fitnesstracker.presentation.navigation.NavigationCallback
import com.example.fitnesstracker.presentation.navigation.Screen
import com.example.fitnesstracker.presentation.ui.theme.Primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StyledTopAppBar(
    onNavigationTo: NavigationCallback,
    title: String,
    contentDescription: String = "Content Description"
) {
    TopAppBar(
        title = {
            Text(
                title
            )
        },
        navigationIcon = {
            IconButton(onClick = { onNavigationTo(Screen.Back) }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = contentDescription,
                    tint = Primary
                )
            }
        },
        windowInsets = WindowInsets(0,0,0,0),
        colors = TopAppBarDefaults.topAppBarColors()
    )
}
