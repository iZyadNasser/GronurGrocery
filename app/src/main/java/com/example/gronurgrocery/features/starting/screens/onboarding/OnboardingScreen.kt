package com.example.gronurgrocery.features.starting.screens.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gronurgrocery.features.ui.theme.GronurGroceryTheme
import com.example.gronurgrocery.features.ui.theme.background

@Composable
fun OnBoardingPager(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = modifier
            .fillMaxSize()
            .background(background)
    ) {
        OnBoardingTopDesign(
            onBoardingPage = OnBoardingPage.First,
            modifier = Modifier
                .fillMaxHeight(0.55f)
        )
        Spacer(modifier = Modifier.height(42.dp))
        Indicator(pageInd = 1)
        Spacer(modifier = Modifier.height(60.dp))
        OnBoardingBottomDesign(onBoardingPage = OnBoardingPage.First)
    }
}


@Composable
fun OnBoardingTopDesign(
    onBoardingPage: OnBoardingPage,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    bottomStart = 40.dp,
                    bottomEnd = 40.dp
                )
            )
            .background(Color.White)
    ) {
        Image(
            painter = painterResource(id = onBoardingPage.imageRes),
            contentDescription = "strawberry image",
            modifier = Modifier
                .fillMaxSize()
        )
    }
}

@Composable
fun OnBoardingBottomDesign(
    onBoardingPage: OnBoardingPage,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(background)
            .padding(
                horizontal = 24.dp
            )
    ) {
        Text(
            text = onBoardingPage.title,
            color = Color.White,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier
                .fillMaxWidth()
        )
        Text(
            text = onBoardingPage.description,
            color = Color.White,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(
                    top = 32.dp
                )
        )
        Spacer(modifier = Modifier.height(72.dp))
        CustomButton("Continue")
    }
}

@Composable
fun CustomButton(
    text: String,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(RoundedCornerShape(30.dp))
            .background(Color.White)
            .fillMaxWidth()
            .height(60.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Preview
@Composable
fun OnBoardingDesignPreview() {
    GronurGroceryTheme {
        OnBoardingPager()
    }
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
    pageInd: Int,
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