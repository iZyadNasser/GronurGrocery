package com.example.gronurgrocery.common.presentation.ui.bottom_navigation

import androidx.annotation.DrawableRes
import com.example.gronurgrocery.R
import com.example.gronurgrocery.common.presentation.navigation.screens.Home
import com.example.gronurgrocery.common.presentation.navigation.screens.More
import com.example.gronurgrocery.common.presentation.navigation.screens.MyCart
import com.example.gronurgrocery.common.presentation.navigation.screens.Order

sealed class BottomNavigationItemInfo(
    val title: String,
    @DrawableRes val icon: Int,
    val route: Any
) {
    data object HomeItemInfo : BottomNavigationItemInfo(
        title = "Home",
        icon = R.drawable.home,
        route = Home("")
    )

    data object OrderItemInfo : BottomNavigationItemInfo(
        title = "Order",
        icon = R.drawable.shop,
        route = Order("")
    )

    data object MyCartItemInfo : BottomNavigationItemInfo(
        title = "My Cart",
        icon = R.drawable.bag,
        route = MyCart("")
    )

    data object MoreItemInfo : BottomNavigationItemInfo(
        title = "More",
        icon = R.drawable.category,
        route = More("")
    )
}