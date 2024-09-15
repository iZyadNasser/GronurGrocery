package com.example.gronurgrocery.common.presentation.ui.bottom_navigation


import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Icon
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gronurgrocery.common.presentation.navigation.screens.Home
import com.example.gronurgrocery.features.ui.theme.GronurGroceryTheme
import com.example.gronurgrocery.features.ui.theme.background


@Composable
fun BottomNavigationBody(
    currentRoute: Any,
    content: @Composable () -> Unit,
    navigateToItem: (Any) -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxSize()
    ) {

        val configuration = LocalConfiguration.current

        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(3f)
                    .background(background)
            ) {
                content()
            }
            BottomNavigationBar(
                currentRoute = currentRoute,
                navigateToItem = {
                    navigateToItem(it)
                },
                modifier = Modifier
                    .navigationBarsPadding()
                    .weight(1f)
            )
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(6f)
                    .background(background)
            ) {
                content()
            }
            BottomNavigationBar(
                currentRoute = currentRoute,
                navigateToItem = {
                    navigateToItem(it)
                },
                modifier = Modifier
                    .navigationBarsPadding()
                    .weight(1f)
            )
        }

    }

}

@Composable
private fun BottomNavigationBar(
    currentRoute: Any,
    navigateToItem: (Any) -> Unit,
    modifier: Modifier = Modifier
) {

    val items = listOf(
        BottomNavigationItemInfo.HomeItemInfo,
        BottomNavigationItemInfo.OrderItemInfo,
        BottomNavigationItemInfo.MyCartItemInfo,
        BottomNavigationItemInfo.MoreItemInfo
    )

    BottomNavigation(
        backgroundColor = background,
        elevation = 0.dp,
        modifier = modifier
    ) {

        items.forEach { item ->

            BottomNavigationItem(
                icon = {
                    Column {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = item.title,
                            tint = if (currentRoute == item.route) Color.White else Color.Gray
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                },
                label = {
                    Text(
                        item.title,
                        color = if (currentRoute == item.route) Color.White else Color.Gray,
                        style = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp
                        )
                    )
                },
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navigateToItem(item.route)
                    }
                },
                alwaysShowLabel = true,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(
                        bottom = 4.dp
                    )
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewBottomNavigation() {
    GronurGroceryTheme {
        BottomNavigationBody(
            currentRoute = Home(""),
            navigateToItem = {},
            content = {
                Column(
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(
                                bottomStart = 40.dp,
                                bottomEnd = 40.dp
                            )
                        )
                        .fillMaxSize()
                        .background(Color.White)
                ) {

                }
            }
        )
    }
}