package com.example.gronurgrocery.features.home.presentation.product_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.gronurgrocery.R
import com.example.gronurgrocery.common.presentation.ui.components.BackButton
import com.example.gronurgrocery.common.presentation.ui.components.CustomButton
import com.example.gronurgrocery.features.home.domain.model.Product
import com.example.gronurgrocery.features.home.domain.model.ProductDetail
import com.example.gronurgrocery.features.home.domain.model.Review
import com.example.gronurgrocery.features.home.domain.model.UserBrief
import com.example.gronurgrocery.features.ui.theme.GronurGroceryTheme
import com.example.gronurgrocery.features.ui.theme.background

@Composable
fun ProductDetailScreenContainer(
    productDetail: ProductDetail,
    content: @Composable (ProductDetail, Modifier) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(background)
            .navigationBarsPadding()
    ) {
        content(
            productDetail,
            Modifier
                .weight(1f)
        )

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
    productDetail: ProductDetail,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
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
        ) {
            ProductDetailScreenHeader(
                imageUrl = productDetail.imageUrl,
                name = productDetail.name
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(24.dp)
        ) {
            BackButton(
                onClick = { /*TODO*/ },
                borderColor = Color(0x3319253D),
                arrowColor = background
            )
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
        modifier = modifier
            .fillMaxWidth()
            .paint(painter = painterResource(id = R.drawable.ellipse), contentScale = ContentScale.FillWidth)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .fillMaxWidth()
                .height(234.dp)
                .paint(painter = painterResource(id = R.drawable.ellipse), contentScale = ContentScale.FillWidth)
                .padding(42.dp)
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewProductDetailScreen() {
    GronurGroceryTheme {
        ProductDetailScreenContainer(
            content = { product, modifier ->
                ProductDetailScreen(productDetail = product, modifier = modifier)
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
                            imageUrl = "",
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