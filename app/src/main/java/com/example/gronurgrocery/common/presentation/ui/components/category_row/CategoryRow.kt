package com.example.gronurgrocery.common.presentation.ui.components.category_row

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gronurgrocery.features.ui.theme.GronurGroceryTheme
import com.example.gronurgrocery.features.ui.theme.background

@Composable
fun CategoryRow(
    items: List<String>,
    modifier: Modifier = Modifier,
    initialSelectedItem: String? = null,
    onItemClick: (String) -> Unit,
    categoryRowViewModel: CategoryRowViewModel = viewModel<CategoryRowViewModel>()
) {

    categoryRowViewModel.initializeSelectedItem(initialSelectedItem)

    val uiState = categoryRowViewModel.state.value

    val newItems = listOf("") + items + listOf("")

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
            .fillMaxWidth()
    ) {
        items(newItems) { item ->
            when (item) {
                "" -> {
                    InvisibleItem()
                }
                uiState.selectedItem -> {
                    CategoryRowItem(
                        onItemClick = {
                            onItemClick(it)
                            categoryRowViewModel.updateSelectedItem(it)
                        },
                        name = item,
                        selected = true
                    )
                }
                else -> {
                    CategoryRowItem(
                        onItemClick = {
                            onItemClick(it)
                            categoryRowViewModel.updateSelectedItem(it)
                        },
                        name = item
                    )
                }
            }
        }
    }
}

@Composable
private fun CategoryRowItem(
    name: String,
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    onItemClick: (String) -> Unit,
) {

    val fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Medium
    val backgroundColor = if (selected) background else Color(0xFFF8F8F8)
    val fontColor = if (selected) Color.White else background

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .height(48.dp)
            .background(backgroundColor)
            .padding(
                horizontal = 20.dp
            )
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onItemClick(name)
                // TODO
            }
    ) {
        Text(
            text = name,
            color = fontColor,
            style = TextStyle(
                fontWeight = fontWeight,
                fontSize = 16.sp
            )
        )
    }
}

@Composable
private fun InvisibleItem(
    modifier: Modifier = Modifier
) {
    Spacer(modifier = modifier.width(12.dp))
}

@Preview
@Composable
private fun PreviewCategoryRowItemSelected() {
    GronurGroceryTheme {
        CategoryRowItem(onItemClick = {  }, name = "Fast-food", selected = true)
    }
}

@Preview
@Composable
private fun PreviewCategoryRowItemNotSelected() {
    GronurGroceryTheme {
        CategoryRowItem(onItemClick = {  }, name = "Fast-food")
    }
}


@Preview(showSystemUi = true)
@Composable
private fun PreviewCategoryRow() {
    GronurGroceryTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = 12.dp,
                    bottom = 12.dp
                )
        ) {
            CategoryRow(
                listOf(
                    "Fruits",
                    "Fast-food",
                    "Vegetables",
                    "Fish"
                ),
                initialSelectedItem = "Fruits",
                onItemClick = {}
            )
        }
    }
}