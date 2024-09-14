package com.example.gronurgrocery.common.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gronurgrocery.features.ui.theme.GronurGroceryTheme
import com.example.gronurgrocery.features.ui.theme.background

@Composable
fun CategoryRow(
    items: List<String>,
    modifier: Modifier = Modifier,
    selectedItem: String? = null
) {

    val newItems = listOf("") + items

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
                selectedItem -> {
                    CategoryRowItem(name = item, selected = true)
                }
                else -> {
                    CategoryRowItem(name = item)
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
    Spacer(modifier = modifier.width(24.dp))
}

@Preview
@Composable
private fun PreviewCategoryRowItemSelected() {
    GronurGroceryTheme {
        CategoryRowItem("Fast-food", selected = true)
    }
}

@Preview
@Composable
private fun PreviewCategoryRowItemNotSelected() {
    GronurGroceryTheme {
        CategoryRowItem("Fast-food")
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
                selectedItem = "Fruits"
            )
        }
    }
}