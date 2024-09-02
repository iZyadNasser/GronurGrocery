package com.example.gronurgrocery.features.starting.presentation.onboarding

import android.annotation.SuppressLint
import android.icu.math.BigDecimal
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.example.gronurgrocery.features.ui.theme.GronurGroceryTheme
import com.example.gronurgrocery.features.ui.theme.background
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import com.example.gronurgrocery.features.starting.presentation.onboarding.components.CustomButton
import com.example.gronurgrocery.features.starting.presentation.onboarding.components.Indicator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

const val PAGE_COUNT = 3

@SuppressLint("DefaultLocale")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingPager(
    modifier: Modifier = Modifier
) {
    val topPagerState = rememberPagerState(pageCount = {PAGE_COUNT})
    val bottomPagerState = rememberPagerState(pageCount = {PAGE_COUNT})
    val screenHeight = LocalConfiguration.current.screenHeightDp
    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third
    )
    val coroutineScope = rememberCoroutineScope()

    val scrollingFollowingPair by remember {
        derivedStateOf {
            if (topPagerState.isScrollInProgress) {
                topPagerState to bottomPagerState
            } else if (bottomPagerState.isScrollInProgress) {
                bottomPagerState to topPagerState
            } else null
        }
    }

    LaunchedEffect(scrollingFollowingPair) {
        val (scrollingState, followingState) = scrollingFollowingPair ?: return@LaunchedEffect
        snapshotFlow { Pair(scrollingState.currentPage, scrollingState.currentPageOffsetFraction) }
            .collect { pagePart ->
                followingState.scrollToPage(
                    pagePart.first,
                    pagePart.second,
                )
            }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            //.verticalScroll(rememberScrollState())
            .statusBarsPadding()
            .safeDrawingPadding()
            .navigationBarsPadding()
            .background(background)

    ) {
        HorizontalPager(
            state = topPagerState
        ) { position ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(background)
            ) {
                OnBoardingTopDesign(
                    onBoardingPage = pages[position],
                    modifier = Modifier.height(screenHeight.times(0.5f).dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(42.dp))
        Indicator(pageInd = topPagerState.currentPage + 1)
        Spacer(modifier = Modifier.height(60.dp))
        HorizontalPager(
            state = bottomPagerState
        ) { position ->
            OnBoardingBottomDesign(onBoardingPage = pages[position])
        }
        Column(
            verticalArrangement = Arrangement.Bottom, modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            CustomButton(
                "Continue",
                onClick = {
                    if (topPagerState.currentPage != PAGE_COUNT - 1) {
                        coroutineScope.launch {
                            topPagerState.scrollToPage(topPagerState.currentPage + 1)
                        }
                        coroutineScope.launch {
                            bottomPagerState.scrollToPage(bottomPagerState.currentPage + 1)
                        }
                    }
                }
            )
            Spacer(modifier = Modifier.height(36.dp))
        }
    }
}


@Composable
fun OnBoardingTopDesign(
    onBoardingPage: OnBoardingPage,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth(0.995f)
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
        //verticalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
            .background(background)
            .padding(
                start = 24.dp,
                end = 24.dp
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
    }
}

@Preview(showSystemUi = true)
@Composable
fun OnBoardingDesignPreview() {
    GronurGroceryTheme {
        OnBoardingPager()
    }
}