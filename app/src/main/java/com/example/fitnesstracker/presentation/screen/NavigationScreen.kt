package com.example.fitnesstracker.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitnesstracker.R
import com.example.fitnesstracker.presentation.navigation.NavigationCallback
import com.example.fitnesstracker.presentation.ui.theme.Primary

data class NavItem(
    val label: String,
    val icon: Painter
)

@Composable
fun NavigationScreen(
    onNavigateTo: NavigationCallback = {}
) {
    val navItemList = listOf(
        NavItem(
            stringResource(id = R.string.activity),
            painterResource(id = R.drawable.activity)
        ),
        NavItem(
            stringResource(id = R.string.profile),
            painterResource(id = R.drawable.person)
        )
    )

    var selectedIndex by remember { mutableIntStateOf(0) }

    Scaffold (
        contentWindowInsets = WindowInsets(0,0,0,0),
        bottomBar = {
            NavigationBar(
                windowInsets = WindowInsets(0,0,0,0),
                containerColor = MaterialTheme.colorScheme.background
            ) {
                navItemList.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                        },
                        icon = {
                            Icon(
                                painter = item.icon,
                                contentDescription = item.label
                            )
                        },
                        label = {
                            Text(
                                text = item.label
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Primary,
                            selectedTextColor = Primary,
                            indicatorColor = MaterialTheme.colorScheme.background
                        )
                    )
                }
            }
        },
    ) { innerPadding ->
        Column (
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            ContentScreen(selectedIndex)
        }
    }
}

@Composable
fun ContentScreen(
    selectedIndex: Int,
    modifier: Modifier = Modifier
) {
    when(selectedIndex){
        0 -> ActivityScreen(modifier)
        1 -> ProfileScreen(modifier)
    }
}

@Composable
@Preview(showBackground = true)
fun NavigationScreenPreview(){
    NavigationScreen()
}