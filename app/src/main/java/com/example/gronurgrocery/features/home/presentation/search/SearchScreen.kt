package com.example.gronurgrocery.features.home.presentation.search

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
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
                    fieldValue = uiState.currentSearchText,
                    onValueChange = { searchViewModel.updateSearchBarState(it) },
                    onFilterIconClick = { searchViewModel.toggleFilterTab() },
                    onFocus = { searchViewModel.toggleFilterTab() },
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        },
        content = {
            SearchScreen(uiState.items)
        },
        onBackButtonPressed = onUpButtonPressed
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
    onFocus: () -> Unit,
    modifier: Modifier = Modifier,
    onActionButtonClick: (() -> Unit)? = null,
) {
    TextField(
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
            .onFocusChanged {
                if (it.hasFocus) {
                    onFocus()
                }
            }
    )
}

@Preview
@Composable
private fun PreviewSearchBar() {
    GronurGroceryTheme {
        SearchBar(
            onFilterIconClick = {},
            onValueChange = {},
            fieldValue = "",
            onFocus = {}
        )
    }
}

@Preview
@Composable
private fun PreviewSearchScreen() {
    GronurGroceryTheme {
        SearchScreenContainer(onUpButtonPressed = { })
    }
}