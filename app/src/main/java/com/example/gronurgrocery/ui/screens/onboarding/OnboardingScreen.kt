package com.example.gronurgrocery.ui.screens.onboarding

import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun OnBoardingDesign(
    @DrawableRes imageId: Int,
    title: String,
    description: String
) {

}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingHorizontalPager() {
    val pagerState = rememberPagerState(pageCount = {
        3
    })
}

@Composable
fun Indicator(
    pageInd: Int
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
    ) {
        IndicatorPoint(
            modifier = Modifier
                .width(36.dp)
        )
    }
}

@Composable
fun IndicatorPoint(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(Color.White)
            .height(6.dp)
    ) {

    }
}

@Preview
@Composable
fun Preview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Indicator(
            pageInd = 2,
        )
    }
}