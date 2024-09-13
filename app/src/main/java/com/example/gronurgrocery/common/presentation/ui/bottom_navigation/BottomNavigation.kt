package com.example.gronurgrocery.common.presentation.ui.bottom_navigation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.gronurgrocery.features.ui.theme.GronurGroceryTheme
import com.example.gronurgrocery.features.ui.theme.background

@Composable
fun BottomNavigationBody(
    content: @Composable () -> Unit,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.BottomStart,
        modifier = modifier
            .fillMaxSize()
    ) {
        content()
        BottomNavigationBar(
            navController = navController,
            modifier = Modifier
                .navigationBarsPadding()
        )
    }

}

@Composable
private fun BottomNavigationBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    val items = listOf(
        BottomNavigationItemInfo.HomeItemInfo,
        BottomNavigationItemInfo.OrderItemInfo,
        BottomNavigationItemInfo.MyCartItemInfo,
        BottomNavigationItemInfo.MoreItemInfo
    )

    BottomNavigation(
        backgroundColor = background
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Image(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title, color = Color.White) },
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route)
                    }
                },
                alwaysShowLabel = true,
                modifier = Modifier
                    .border(BorderStroke(1.dp, background))
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewBottomNavigation() {
    GronurGroceryTheme {
        BottomNavigationBody(
            navController = rememberNavController(),
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(background)
                        .verticalScroll(rememberScrollState())
                ) {

                }
            }
        )
    }
}