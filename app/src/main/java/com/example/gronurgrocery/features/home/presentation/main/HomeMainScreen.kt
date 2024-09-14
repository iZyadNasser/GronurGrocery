package com.example.gronurgrocery.features.home.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gronurgrocery.common.presentation.navigation.screens.Home
import com.example.gronurgrocery.common.presentation.ui.bottom_navigation.BottomNavigationBody
import com.example.gronurgrocery.common.presentation.ui.components.AppTitle
import com.example.gronurgrocery.common.presentation.ui.components.CategoryRow
import com.example.gronurgrocery.common.presentation.ui.components.MainPagesHeader
import com.example.gronurgrocery.features.ui.theme.GronurGroceryTheme

@Composable
fun HomeMainScreen(
    modifier: Modifier = Modifier
) {
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
            modifier = Modifier
                .safeDrawingPadding()
                .statusBarsPadding()
                .padding(
                    top = 16.dp,
                    start = 24.dp,
                    end = 24.dp
                )
        )

        Spacer(modifier = Modifier.height(40.dp))
        CategoryRow(
            // TODO (Remove these placeholder data with real data)
            listOf(
                "Fruits",
                "Fast-food",
                "Vegetables",
                "Fish"
            ),
            selectedItem = "Fruits"
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewHomeMainScreen() {
    GronurGroceryTheme {
        BottomNavigationBody(currentRoute = Home, content = { HomeMainScreen() }, {})
    }
}