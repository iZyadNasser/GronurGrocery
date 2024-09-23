package com.example.gronurgrocery.features.home.presentation.components.item_grid

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gronurgrocery.features.home.data.source.remote.dto.toDomain
import com.example.gronurgrocery.features.home.data.source.testing_source.ProductsTestingSource
import com.example.gronurgrocery.features.home.domain.model.Product
import com.example.gronurgrocery.features.ui.theme.GronurGroceryTheme

@Composable
fun ItemsGrid(
    items: List<Product>,
    modifier: Modifier = Modifier
) {

    LazyVerticalGrid(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize()
    ) {

        val newItems = listOf(Product("", "", "", "")) + listOf(Product("", "", "", "")) + items + listOf(Product("", "", "", "")) + listOf(Product("", "", "", ""))

        items(newItems) { item ->
            if (item.name != "") {
                ItemsGridItem(
                    product = item
                )
            } else {
                InvisibleItem()
            }
        }
    }
}

@Composable
private fun InvisibleItem(
    modifier: Modifier = Modifier
) {
    Spacer(modifier = Modifier.size(12.dp))
}

@Preview
@Composable
private fun PreviewItemsGrid() {
    GronurGroceryTheme {
        ItemsGrid(items = ProductsTestingSource.fruits.map { it.toDomain() })
    }
}