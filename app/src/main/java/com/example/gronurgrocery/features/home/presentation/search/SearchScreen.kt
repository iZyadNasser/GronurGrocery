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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.SliderState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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
                    start = 24.dp,
                    end = 24.dp,
                    top = 32.dp
                )

        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
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
                color = Color(0x1A96A4B2)
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Price Range",
                color = background,
                style = TextStyle(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Row(
                    modifier = Modifier
                        .width(30.dp)
                        .height(10.dp)
                        .background(background)
                    ) {

                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Slider(
                        valueRange = 50f..100f,
                        steps = 10,
                        value = uiState.searchFilter.priceRange.minPrice,
                        onValueChange = {
                            searchViewModel.updatePriceRangeState(
                                PriceRange(
                                    it,
                                    uiState.searchFilter.priceRange.maxPrice
                                )
                            )
                        },
                        colors = SliderDefaults.colors(
                            thumbColor = background,
                            activeTrackColor = Color(0x3319253D),
                            activeTickColor = Color(0x3319253D),
                            inactiveTrackColor = background,
                            inactiveTickColor = background
                        ),

                        thumb = {
                            Image(
                                painter = painterResource(id = R.drawable.slider_indicator),
                                contentDescription = "drag",
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                            )
                        },
                        track = { sliderState ->
                            val colorStops = arrayOf(
                                0.0f to Color(0x3319253D),
                                ((sliderState.value - 50) / 50) to Color(0x3319253D),
                                ((sliderState.value - 50) / 50) to background,
                                1f to background
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
                    Slider(
                        valueRange = 50f..100f,
                        steps = 10,
                        value = uiState.searchFilter.priceRange.maxPrice,
                        onValueChange = {
                            searchViewModel.updatePriceRangeState(
                                PriceRange(
                                    uiState.searchFilter.priceRange.minPrice,
                                    it
                                )
                            )
                        },
                        colors = SliderDefaults.colors(
                            thumbColor = background,
                            activeTrackColor = background,
                            activeTickColor = Color(0x3319253D),
                            inactiveTrackColor = Color(0x3319253D),
                            inactiveTickColor = Color(0x3319253D)
                        ),
                        thumb = {
                            Image(
                                painter = painterResource(id = R.drawable.slider_indicator),
                                contentDescription = "drag",
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                            )
                        },
                        track = { sliderState ->
                            val colorStops = arrayOf(
                                0.0f to background,
                                ((sliderState.value - 50) / 50) to background,
                                ((sliderState.value - 50) / 50) to Color(0x3319253D),
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
            }

        }
    }
}

@Composable
private fun PriceRange(modifier: Modifier = Modifier) {

}

@Preview
@Composable
private fun PreviewSearchScreen() {
    GronurGroceryTheme {
        SearchScreenContainer(onUpButtonPressed = { })
    }
}