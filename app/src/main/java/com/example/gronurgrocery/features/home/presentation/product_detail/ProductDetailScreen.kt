package com.example.gronurgrocery.features.home.presentation.product_detail

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.gronurgrocery.R
import com.example.gronurgrocery.common.presentation.ui.components.BackButton
import com.example.gronurgrocery.common.presentation.ui.components.CustomButton
import com.example.gronurgrocery.features.home.domain.model.Product
import com.example.gronurgrocery.features.home.domain.model.ProductDetail
import com.example.gronurgrocery.features.home.domain.model.Review
import com.example.gronurgrocery.features.home.domain.model.UserBrief
import com.example.gronurgrocery.features.home.presentation.components.item_grid.ItemsGrid
import com.example.gronurgrocery.features.ui.theme.GronurGroceryTheme
import com.example.gronurgrocery.features.ui.theme.background

@Composable
fun ProductDetailScreenContainer(
    onUpButtonPressed: () -> Unit,
    productDetail: ProductDetail,
    content: @Composable (ProductDetail, Modifier, () -> Unit) -> Unit,
    modifier: Modifier = Modifier
) {
    val colorStops = arrayOf(
        0.5f to Color(0xFFF8F8F8),
        0.6f to background,
        1f to background
    )
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colorStops = colorStops,
                )
            )
            .navigationBarsPadding()
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
        ) {
            content(
                productDetail,
                Modifier
                    .fillMaxSize()
                    .statusBarsPadding(),
                onUpButtonPressed
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(22.dp  ),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(horizontal = 24.dp)
        ) {
            Price(price = productDetail.price)
            AddToCartButton(onClick = {})
        }
    }
}

@Composable
private fun Price(
    price: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.Bottom,
        modifier = modifier
    ) {
        Text(
            text = "$${price}",
            color = Color.White,
            style = TextStyle(
                fontWeight = FontWeight.SemiBold,
                fontSize = 26.sp
            ),
            softWrap = false,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = "/",
            color = Color(0x80FFFFFF),
            style = TextStyle(
                fontWeight = FontWeight.SemiBold,
                fontSize = 26.sp
            )
        )
        Text(
            text = "kg",
            color = Color(0x80FFFFFF),
            style = TextStyle(
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
        )
    }
}

@Composable
private fun AddToCartButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    CustomButton(
        text = "Add to cart",
        textColor = background,
        backgroundColor = Color.White,
        onClick = { onClick() },
        modifier = modifier
    )
}

@Composable
fun ProductDetailScreen(
    onUpButtonPressed: () -> Unit,
    productDetail: ProductDetail,
    modifier: Modifier = Modifier,
    productDetailViewModel: ProductDetailViewModel = viewModel<ProductDetailViewModel>()
) {

    productDetailViewModel.initializeMaxAmount(productDetail.maxAmount)
    val uiState = productDetailViewModel.state.value

    Box(
        modifier = modifier

    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
                .background(Color(0xFFF8F8F8))
                .statusBarsPadding()
        )
        Column(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        bottomStart = 40.dp,
                        bottomEnd = 40.dp
                    )
                )
                .background(Color.White)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            ProductDetailScreenHeader(
                imageUrl = productDetail.imageUrl,
                name = productDetail.name
            )

            Spacer(modifier = Modifier.height(32.dp))
            Column(
                modifier = Modifier
                    .statusBarsPadding()
            ) {
                Title(
                    onButtonClick = {
                        productDetailViewModel.changeAmount(it)
                    },
                    name = productDetail.name,
                    currentAmount = uiState.currentAmount
                )

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = if (productDetail.isAvailable) "Available in stock" else "Out of stock",
                    color = Color(0xFF96A4B2),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal
                    ),
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                )

                Spacer(modifier = Modifier.height(28.dp))
                Description(productDetail.description)

                Spacer(modifier = Modifier.height(28.dp))
                // Reviews
                Column(
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                ) {
                    Text(
                        text = "Product Reviews",
                        color = background,
                        style = TextStyle(
                            fontSize = 22.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                    //ProductReview(review = productDetail.productReviews[0])
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            val newItems = productDetail.productReviews + listOf(null)
                            items(newItems) { review ->
                                if (review == null) {
                                    Spacer(modifier = Modifier.height(16.dp))
                                } else {
                                    ProductReview(review = review)

                                    if (newItems[newItems.indexOf(review) + 1] != null) {
                                        Spacer(
                                            modifier = Modifier
                                                .padding(
                                                    vertical = 12.dp
                                                )
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
                Column(
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                ) {
                    Text(
                        text = "Similar Products",
                        color = background,
                        style = TextStyle(
                            fontSize = 22.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    ) {
                        ItemsGrid(items = productDetail.similarProducts)
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            BackButton(
                onClick = { onUpButtonPressed() },
                borderColor = Color(0x3319253D),
                arrowColor = background,
                backgroundColor = Color(0xFFF8F8F8)
            )
        }
    }
}

@Composable
private fun ProductReview(
    review: Review,
    modifier: Modifier = Modifier
) {
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()

        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxHeight()
            ) {
                AsyncImage(
                    model = review.user.imageUrl,
                    contentDescription = review.user.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(RoundedCornerShape(25.dp))
                        .size(50.dp)
                        .background(Color(0xFF96A4B2))
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    Text(
                        text = review.user.name,
                        color = background,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row {
                        for (i in 1..review.rating) {
                            Icon(
                                painter = painterResource(id = R.drawable.star),
                                contentDescription = "rating star",
                                tint = Color(0xFFFF6C3D)
                            )

                            if (i != review.rating) {
                                Spacer(modifier = Modifier.width(4.dp))
                            }
                        }
                    }
                }
            }
            Text(
                text = review.date,
                color = Color(0xFF96A4B2),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = review.review,
            color = Color(0xFF96A4B2),
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 24.sp
            )
        )
    }

}

@Preview
@Composable
private fun PreviewProductReview() {
    GronurGroceryTheme {
        ProductReview(
            review = Review(
                user = UserBrief(
                    userId = "",
                    imageUrl = "https://t3.ftcdn.net/jpg/02/43/12/34/360_F_243123463_zTooub557xEWABDLk0jJklDyLSGl2jrr.jpg",
                    name = "Victor Flexin"
                ),
                rating = 5,
                date = "18 Sep, 2023",
                review = "The dial on this timepiece is extremely unique , Next time I want to buy it again."
            )
        )
    }
}

@Composable
private fun Description(description: String) {
    Column(
        modifier = Modifier
            .padding(horizontal = 24.dp)
    ) {
        Text(
            text = "Product Description",
            color = background,
            style = TextStyle(
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold
            )
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = description,
            color = Color(0xFF96A4B2),
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 24.sp
            )
        )
    }
}

@Composable
private fun Title(
    onButtonClick: (Boolean) -> Unit,
    name: String,
    currentAmount: Int,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 24.dp
            )
    ) {
        Text(
            text = name,
            color = background,
            style = TextStyle(
                fontSize = 32.sp,
                fontWeight = FontWeight.SemiBold
            )
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .size(32.dp)
                    .background(background)
                    .clickable {
                        onButtonClick(false)
                    }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.minus),
                    contentDescription = "Reduce Amount",
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "$currentAmount kg",
                color = background,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .size(32.dp)
                    .background(background)
                    .clickable {
                        onButtonClick(true)
                    }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.add),
                    contentDescription = "Reduce Amount",
                    tint = Color.White
                )
            }

        }
    }
}

@Composable
private fun ProductDetailScreenHeader(
    imageUrl: String,
    name: String,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .paint(
                painter = painterResource(id = R.drawable.ellipse),
                contentScale = ContentScale.FillWidth
            )
            .height(234.dp)
            .statusBarsPadding()
            .padding(
                start = 56.dp,
                end = 24.dp,
                bottom = 36.dp,
                top = 36.dp
            )

    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = name,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewProductDetailScreen() {
    GronurGroceryTheme {
        ProductDetailScreenContainer(
            onUpButtonPressed = {},
            content = { product, modifier, onUpButtonPressed ->
                ProductDetailScreen(productDetail = product, modifier = modifier, onUpButtonPressed = onUpButtonPressed)
            },
            productDetail = ProductDetail(
                imageUrl = "https://w7.pngwing.com/pngs/736/5/png-transparent-sugar-apple-graphy-fruit-desktop-apple-thumbnail.png",
                name = "Fresh Orange",
                isAvailable = true,
                maxAmount = 30,
                description = "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour.",
                productReviews = listOf(
                    Review(
                        user = UserBrief(
                            userId = "",
                            imageUrl = "https://t3.ftcdn.net/jpg/02/43/12/34/360_F_243123463_zTooub557xEWABDLk0jJklDyLSGl2jrr.jpg",
                            name = "Victor Flexin"
                        ),
                        rating = 5,
                        date = "18 Sep, 2023",
                        review = "The dial on this timepiece is extremely unique , Next time I want to buy it again."
                    ),
                    Review(
                        user = UserBrief(
                            userId = "",
                            imageUrl = "https://t3.ftcdn.net/jpg/02/43/12/34/360_F_243123463_zTooub557xEWABDLk0jJklDyLSGl2jrr.jpg",
                            name = "Victor Flexin"
                        ),
                        rating = 5,
                        date = "18 Sep, 2023",
                        review = "The dial on this timepiece is extremely unique , Next time I want to buy it again."
                    ),
                    Review(
                        user = UserBrief(
                            userId = "",
                            imageUrl = "https://t3.ftcdn.net/jpg/02/43/12/34/360_F_243123463_zTooub557xEWABDLk0jJklDyLSGl2jrr.jpg",
                            name = "Victor Flexin"
                        ),
                        rating = 5,
                        date = "18 Sep, 2023",
                        review = "The dial on this timepiece is extremely unique , Next time I want to buy it again."
                    )

                ),
                similarProducts = listOf(
                    Product(
                        imageUrl = "",
                        name = "Strawberry",
                        cals = "75",
                        price = "14.75"
                    ),
                    Product(
                        imageUrl = "",
                        name = "Capsicum",
                        cals = "52",
                        price = "75.68"
                    )
                ),
                price = "14.75"
            )
        )
    }
}