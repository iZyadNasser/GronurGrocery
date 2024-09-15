package com.example.gronurgrocery.features.home.presentation.category_products

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gronurgrocery.common.presentation.ui.components.DarkPageContainerWithBackButton
import com.example.gronurgrocery.features.home.presentation.components.item_grid.ItemsGrid
import com.example.gronurgrocery.features.ui.theme.GronurGroceryTheme

@Composable
fun CategoryProductsScreenContainer(
    onUpButtonPressed: () -> Unit,
    title: String,
    modifier: Modifier = Modifier
) {
    DarkPageContainerWithBackButton(title = title, content = { CategoryProductsScreen() }, modifier = modifier, onBackButtonPressed = onUpButtonPressed)
}

@Composable
private fun CategoryProductsScreen(
    modifier: Modifier = Modifier,
    categoryProductsViewModel: CategoryProductsViewModel = hiltViewModel()
) {

    val uiState = categoryProductsViewModel.state.value

    Column(
        modifier = modifier
            .clip(
                RoundedCornerShape(
                    topStart = 32.dp,
                    topEnd = 32.dp
                )
            )
            .fillMaxSize()
            .background(Color.White)
    ) {
        ItemsGrid(items = uiState.productList)
    }
}

@Preview
@Composable
private fun PreviewCategoryProductsScreen() {
    GronurGroceryTheme {
        CategoryProductsScreenContainer({}, "Fruits")
    }
}