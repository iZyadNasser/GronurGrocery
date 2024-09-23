package com.example.gronurgrocery.features.home.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gronurgrocery.common.presentation.navigation.screens.Home
import com.example.gronurgrocery.common.presentation.ui.bottom_navigation.BottomNavigationBody
import com.example.gronurgrocery.common.presentation.ui.components.MainPagesHeader
import com.example.gronurgrocery.common.presentation.ui.components.category_row.CategoryRow
import com.example.gronurgrocery.features.home.presentation.components.item_grid.ItemsGrid
import com.example.gronurgrocery.features.ui.theme.GronurGroceryTheme
import com.example.gronurgrocery.features.ui.theme.background

@Composable
fun HomeMainScreen(
    onSeeAllClick: (String) -> Unit,
    onSearchIconClick: () -> Unit,
    modifier: Modifier = Modifier,
    homeMainViewModel: HomeMainViewModel = hiltViewModel()
) {

    val uiState = homeMainViewModel.state.value

    Column(
        modifier = modifier
            .clip(
                RoundedCornerShape(
                    bottomStart = 40.dp,
                    bottomEnd = 40.dp
                )
            )
            .fillMaxSize()
            .background(Color.White)

    ) {
        MainPagesHeader(
            onSearchIconClick = onSearchIconClick,
            modifier = Modifier
                .statusBarsPadding()
                .padding(
                    top = 16.dp,
                    start = 24.dp,
                    end = 24.dp
                )
        )

        Spacer(modifier = Modifier.height(40.dp))

        Column {
            CategoryRow(
                items = uiState.categoryList,
                initialSelectedItem = "Fruits",
                onItemClick = {
                    homeMainViewModel.updateCurrentCategory(it)
                }
            )

            Spacer(modifier = Modifier.height(36.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 24.dp
                    )
            ) {
                Text(
                    text = "Popular Fruits",
                    color = background,
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 22.sp
                    )
                )

                Text(
                    text = "See all",
                    color = Color(0xFF96A4B2),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal
                    ),
                    modifier = Modifier
                        .clickable {
                            onSeeAllClick(uiState.category)
                        }
                )
            }

            Spacer(modifier = Modifier.height(12.dp))
            ItemsGrid(
                items = uiState.productList,
                modifier = Modifier
                    .padding(
                        start = 24.dp,
                        end = 24.dp,
                        top = 12.dp
                    )
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewHomeMainScreen() {
    GronurGroceryTheme {
        BottomNavigationBody(currentRoute = Home, content = { HomeMainScreen({}, {}) }, {})
    }
}