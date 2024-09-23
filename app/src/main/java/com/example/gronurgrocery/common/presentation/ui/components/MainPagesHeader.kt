package com.example.gronurgrocery.common.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gronurgrocery.R
import com.example.gronurgrocery.features.ui.theme.GronurGroceryTheme

@Composable
fun MainPagesHeader(
    onSearchIconClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
    ) {
        AppTitle(title = "Daily\nGrocery Food")
        SearchButton(
            onSearchIconClick = onSearchIconClick
        )
    }
}

@Composable
private fun SearchButton(
    onSearchIconClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(RoundedCornerShape(36.dp))
            .width(72.dp)
            .height(100.dp)
            .border(
                width = 3.dp,
                color = Color(0xFFF8F8F8),
                shape = RoundedCornerShape(36.dp)
            )
            .clickable {
                onSearchIconClick()
            }
    ) {
        Icon(painter = painterResource(id = R.drawable.search), contentDescription = "search")
    }
}

@Preview
@Composable
private fun PreviewSearchButton() {
    GronurGroceryTheme {
        SearchButton({})
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewMainHeader() {
    GronurGroceryTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(
                    top = 16.dp,
                    start = 24.dp,
                    end = 24.dp
                )
        ) {
            MainPagesHeader({})
        }
    }
}