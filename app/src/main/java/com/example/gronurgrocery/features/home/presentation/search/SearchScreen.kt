package com.example.gronurgrocery.features.home.presentation.search

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gronurgrocery.R
import com.example.gronurgrocery.common.presentation.ui.components.DarkPageContainerWithBackButton
import com.example.gronurgrocery.common.presentation.ui.components.FormButton
import com.example.gronurgrocery.features.home.domain.model.Product
import com.example.gronurgrocery.features.home.presentation.components.item_grid.ItemsGrid
import com.example.gronurgrocery.features.home.presentation.search.search_state.PriceRange
import com.example.gronurgrocery.features.ui.theme.GronurGroceryTheme
import com.example.gronurgrocery.features.ui.theme.background

@Composable
fun SearchScreenContainer(
    onUpButtonPressed: () -> Unit,
    searchViewModel: SearchViewModel = viewModel<SearchViewModel>()
) {

    val uiState = searchViewModel.state.value
    var toggle: (() -> Unit)? = null
    if (uiState.isFilterTabOpen) {
        toggle = { searchViewModel.toggleFilterTab() }
    }

    DarkPageContainerWithBackButton(
        putMask = toggle,
        onContainerClick = { searchViewModel.toggleFilterTab() },
        title = "Search",
        preContent = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 24.dp
                    )
            ) {
                SearchBar(
                    isDisabled = uiState.isFilterTabOpen,
                    fieldValue = uiState.currentSearchText,
                    onValueChange = { searchViewModel.updateSearchBarState(it) },
                    onFilterIconClick = { searchViewModel.toggleFilterTab() },
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        },
        content = {
            if (!uiState.isFilterTabOpen) {
                SearchScreen(uiState.items)
            } else {
                FilterTab(
                    onResetButtonClick = { searchViewModel.resetFilters() },
                    searchViewModel = searchViewModel
                )
            }
        },
        onBackButtonPressed = {
            if (!uiState.isFilterTabOpen) {
                onUpButtonPressed()
            } else {
                searchViewModel.toggleFilterTab()
            }
        }
    )

}

@Composable
fun SearchScreen(
    items: List<Product>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
            ) {

            }
    ) {
        Spacer(modifier = Modifier.height(24.dp))
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
                .navigationBarsPadding()
                .padding(
                    horizontal = 24.dp
                )

        ) {
            ItemsGrid(items = items)
        }
    }
}

@Composable
private fun SearchBar(
    fieldValue: String,
    onValueChange: (String) -> Unit,
    onFilterIconClick: () -> Unit,
    modifier: Modifier = Modifier,
    isDisabled: Boolean = false,
    onActionButtonClick: (() -> Unit)? = null,
) {
    TextField(
        enabled = !isDisabled,
        value = fieldValue,
        onValueChange = { onValueChange(it) },
        singleLine = true,
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.search_normal_small),
                contentDescription = "email",
                tint = background
            )
        },
        trailingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.setting_4),
                contentDescription = "toggle password visibility",
                tint = background,
                modifier = Modifier
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() },
                    ) {
                        onFilterIconClick()
                    }
                    .animateContentSize()
            )
        },
        placeholder = {
            Text(
                text = "Search for a product",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Start,
                color = Color(0xFF96A4B2)
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            cursorColor = background
        ),
        textStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            lineHeight = 26.sp,
            letterSpacing = 0.sp,
            //fontFamily = FontFamily(Font(R.font.inter_bold)),
            textAlign = TextAlign.Start
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        keyboardActions = if (onActionButtonClick != null) {
            KeyboardActions(
                onDone = {
                    onActionButtonClick()
                }
            )
        } else {
            KeyboardActions.Default
        },
        modifier = modifier
            .clip(RoundedCornerShape(30.dp))
            .height(60.dp)
            .background(Color.White)
    )
}

@Preview
@Composable
private fun PreviewSearchBar() {
    GronurGroceryTheme {
        SearchBar(
            onFilterIconClick = {},
            onValueChange = {},
            fieldValue = ""
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FilterTab(
    modifier: Modifier = Modifier,
    searchViewModel: SearchViewModel,
    onResetButtonClick: () -> Unit
) {

    val uiState = searchViewModel.state.value

    Column(
        modifier = modifier
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
            ) {

            }
    ) {
        Spacer(modifier = Modifier.height(24.dp))
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
                .navigationBarsPadding()
                .padding(
                    top = 32.dp
                )
                .verticalScroll(rememberScrollState())

        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 24.dp,
                        end = 24.dp,
                    )
            ) {
                Text(
                    text = "Filter",
                    color = background,
                    style = TextStyle(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .size(40.dp)
                        .background(Color(0xFFF8F8F8))
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            onResetButtonClick()
                        }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.refresh_2),
                        contentDescription = "Reset Filters"
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Divider(
                color = Color(0x1A96A4B2),
                modifier = Modifier
                    .padding(
                        start = 24.dp,
                        end = 24.dp,
                    )
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Price Range",
                color = background,
                style = TextStyle(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier
                    .padding(
                        start = 24.dp,
                        end = 24.dp,
                    )
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Range Slider
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(
                        start = 24.dp,
                        end = 24.dp,
                    )
            ) {
                RangeSlider(
                    valueRange = 1f..100f,
                    steps = 100,
                    value = uiState.searchFilter.priceRange.minPrice..uiState.searchFilter.priceRange.maxPrice,
                    onValueChange = {
                        searchViewModel.updatePriceRangeState(
                            PriceRange(
                                it.start,
                                it.endInclusive
                            )
                        )
                    },
                    colors = SliderDefaults.colors(
                        thumbColor = background,
                        activeTrackColor = background,
                        activeTickColor = background,
                        inactiveTrackColor = Color(0x3319253D),
                        inactiveTickColor = Color(0x3319253D)
                    ),

                    startThumb = {
                        Image(
                            painter = painterResource(id = R.drawable.slider_indicator),
                            contentDescription = "drag",
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                        )
                    },
                    endThumb = {
                        Image(
                            painter = painterResource(id = R.drawable.slider_indicator),
                            contentDescription = "drag",
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                        )
                    },
                    track = {
                        val colorStops = arrayOf(
                            0.0f to Color(0x3319253D),
                            ((uiState.searchFilter.priceRange.minPrice) / 100) to Color(0x3319253D),
                            ((uiState.searchFilter.priceRange.minPrice) / 100) to background,
                            ((uiState.searchFilter.priceRange.maxPrice) / 100) to background,
                            ((uiState.searchFilter.priceRange.maxPrice) / 100) to Color(0x3319253D),
                            1f to Color(0x3319253D)
                        )
                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .fillMaxWidth()
                                .height(10.dp)
                                .background(
                                    brush = Brush.horizontalGradient(
                                        colorStops = colorStops
                                    )
                                )
                        ) {

                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp)
                )
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 9.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .defaultMinSize(minWidth = 30.dp)
                        .fillMaxWidth((uiState.searchFilter.priceRange.minPrice / 100) + 0.01f)
                ) {
                    Text(
                        text = "$${uiState.searchFilter.priceRange.minPrice.toInt()}",
                        color = background,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                        )
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .defaultMinSize(minWidth = 50.dp)
                        .fillMaxWidth(1 - (uiState.searchFilter.priceRange.maxPrice / 100) + 0.01f)
                ) {
                    Text(
                        text = "$${uiState.searchFilter.priceRange.maxPrice.toInt()}",
                        color = background,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                        )
                    )
                }
            }

            // Categories
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Categories",
                color = background,
                style = TextStyle(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier
                    .padding(
                        start = 24.dp,
                        end = 24.dp,
                    )
            )

            Spacer(modifier = Modifier.height(12.dp))
            val newItems = listOf("") + uiState.searchFilter.categories + listOf("")

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
                        in uiState.searchFilter.categoriesChoice -> {
                            CategoryRowItem(
                                onItemClick = {
                                    searchViewModel.updateCurrentCategories(it)
                                },
                                name = item,
                                selected = true
                            )
                        }
                        else -> {
                            CategoryRowItem(
                                onItemClick = {
                                    searchViewModel.updateCurrentCategories(it)
                                },
                                name = item
                            )
                        }
                    }
                }
            }

            // Recent search
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Recently Search",
                color = background,
                style = TextStyle(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier
                    .padding(
                        start = 24.dp,
                        end = 24.dp,
                    )
            )

            Spacer(modifier = Modifier.height(12.dp))

            val newSearch = uiState.searchFilter.recentSearch + listOf("")

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 24.dp
                    )
                    .height(130.dp)
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    items(newSearch) { item ->
                        when (item) {
                            "" -> {
                                InvisibleItem()
                            }
                            uiState.searchFilter.recentSearchChoice -> {
                                CategoryRowItem(
                                    onItemClick = {
                                        searchViewModel.updateCurrentSearch(it)
                                    },
                                    name = item,
                                    selected = true
                                )
                            }
                            else -> {
                                CategoryRowItem(
                                    onItemClick = {
                                        searchViewModel.updateCurrentSearch(it)
                                    },
                                    name = item
                                )
                            }
                        }
                    }
                }
            }


            // Apply now button
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 24.dp
                    )
            ) {
                FormButton(
                    text = "Apply Now",
                    onClick = { /*TODO*/ }
                )
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
    Spacer(modifier = modifier.width(24.dp))
}

@Preview
@Composable
private fun PreviewSearchScreen() {
    GronurGroceryTheme {
        SearchScreenContainer(onUpButtonPressed = { })
    }
}