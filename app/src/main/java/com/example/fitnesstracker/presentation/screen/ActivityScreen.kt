package com.example.fitnesstracker.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnesstracker.R
import com.example.fitnesstracker.presentation.ui.theme.Primary
import kotlinx.coroutines.launch

@Composable
fun ActivityScreen(
    modifier: Modifier = Modifier
) {
    Column {
        val pageState = rememberPagerState(
            pageCount = { 2 }
        )
        val coroutineScope = rememberCoroutineScope()
        TabRow(
            selectedTabIndex = pageState.currentPage,
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = Primary,
            divider = {},
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = modifier
                        .tabIndicatorOffset(tabPositions[pageState.currentPage]),
                    height = 2.dp,
                    color = Primary
                )
            }
        ) {
            Tab(
                selected = pageState.currentPage == 0,
                text = {
                    Text(
                        text = stringResource(id = R.string.my_activity)
                    )
                },
                onClick = {
                    coroutineScope.launch {
                        pageState.scrollToPage(0)
                    }
                }
            )
            Tab(
                selected = pageState.currentPage == 1,
                text = {
                    Text(
                        text = stringResource(id = R.string.users_activity)
                    )
                },
                onClick = {
                    coroutineScope.launch {
                        pageState.scrollToPage(1)
                    }
                }
            )
        }
        HorizontalPager(
            state = pageState,
            userScrollEnabled = true,
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End
            ) {
                FloatingActionButton(
                    onClick = {}
                ) { }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ActivityScreenPreview() {
    ActivityScreen()
}