package com.example.gronurgrocery.common.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.gronurgrocery.common.presentation.navigation.screens.AccountInfo
import com.example.gronurgrocery.common.presentation.navigation.screens.CategoryProductsList
import com.example.gronurgrocery.common.presentation.navigation.screens.DeliveryAddress
import com.example.gronurgrocery.common.presentation.navigation.screens.ForgotPassword
import com.example.gronurgrocery.common.presentation.navigation.screens.Home
import com.example.gronurgrocery.common.presentation.navigation.screens.Login
import com.example.gronurgrocery.common.presentation.navigation.screens.More
import com.example.gronurgrocery.common.presentation.navigation.screens.MyCart
import com.example.gronurgrocery.common.presentation.navigation.screens.Onboarding
import com.example.gronurgrocery.common.presentation.navigation.screens.Order
import com.example.gronurgrocery.common.presentation.navigation.screens.PaymentMethod
import com.example.gronurgrocery.common.presentation.navigation.screens.ProfileMain
import com.example.gronurgrocery.common.presentation.navigation.screens.Register
import com.example.gronurgrocery.common.presentation.navigation.screens.ResetPassword
import com.example.gronurgrocery.common.presentation.navigation.screens.Search
import com.example.gronurgrocery.common.presentation.navigation.screens.SetUpAccount
import com.example.gronurgrocery.common.presentation.navigation.screens.Splash
import com.example.gronurgrocery.common.presentation.navigation.screens.Verification
import com.example.gronurgrocery.common.presentation.navigation.screens.VerificationSource
import com.example.gronurgrocery.common.presentation.ui.bottom_navigation.BottomNavigationBody
import com.example.gronurgrocery.features.auth.domain.model.RegisterResponse
import com.example.gronurgrocery.features.auth.presentation.forgot_password.ForgotPasswordScreen
import com.example.gronurgrocery.features.auth.presentation.login.LoginScreen
import com.example.gronurgrocery.features.auth.presentation.register.RegData
import com.example.gronurgrocery.features.auth.presentation.register.RegisterScreen
import com.example.gronurgrocery.features.auth.presentation.reset_password.ResetPasswordScreen
import com.example.gronurgrocery.features.auth.presentation.set_up_account.SetUpAccountScreen
import com.example.gronurgrocery.features.auth.presentation.verification.VerificationScreen
import com.example.gronurgrocery.features.home.domain.model.Product
import com.example.gronurgrocery.features.home.domain.model.ProductDetail
import com.example.gronurgrocery.features.home.domain.model.Review
import com.example.gronurgrocery.features.home.domain.model.UserBrief
import com.example.gronurgrocery.features.home.presentation.category_products.CategoryProductsScreenContainer
import com.example.gronurgrocery.features.home.presentation.main.HomeMainScreen
import com.example.gronurgrocery.features.home.presentation.product_detail.ProductDetailScreen
import com.example.gronurgrocery.features.home.presentation.product_detail.ProductDetailScreenContainer
import com.example.gronurgrocery.features.home.presentation.search.SearchScreenContainer
import com.example.gronurgrocery.features.profile.presentation.account_info.AccountInfo
import com.example.gronurgrocery.features.profile.presentation.address.DeliveryAddress
import com.example.gronurgrocery.features.profile.presentation.payment.PaymentMethod
import com.example.gronurgrocery.features.profile.presentation.profile_main.ProfileMain
import com.example.gronurgrocery.features.starting.presentation.onboarding.OnboardingPager
import com.example.gronurgrocery.features.starting.presentation.splash.SplashScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

const val SPLASH_DELAY_TIME = 2000L

@Composable
fun MyApp(
    navController: NavHostController,
    viewModel: MainAppViewModel = hiltViewModel()
) {
    NavHost(
        navController = navController,
        startDestination = PaymentMethod
    ) {
        composable<Splash> {
            val coroutineScope = rememberCoroutineScope()
            LaunchedEffect(key1 = true) {
                coroutineScope.launch {
                    delay(SPLASH_DELAY_TIME)
                    if (viewModel.onboardingState.value) {
                        if (viewModel.userTokenState.value.isEmpty()) {
                            navController.navigate(route = Register) {
                                popUpTo(route = Splash) {
                                    inclusive = true
                                }
                            }
                        } else {
                            navController.navigate(route = Home(viewModel.userTokenState.value)) {
                                popUpTo(route = Splash) {
                                    inclusive = true
                                }
                            }
                        }
                    } else {
                        navController.navigate(route = Onboarding) {
                            popUpTo(route = Splash) {
                                inclusive = true
                            }
                        }
                    }
                }
            }

            SplashScreen()
        }

        composable<Onboarding> {
            OnboardingPager(
                onBackPressed = { navController.popBackStack() },
                onLastContinuePressed = {
                    navController.navigate(route = Register) {
                        popUpTo(route = Onboarding) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable<Register> {
            RegisterScreen(
                onSignInClick = {
                    navController.navigate(route = Login())
                },
                onSignUpClick = { regData ->

                    navController.navigate(
                        route = Verification(
                            source = VerificationSource.RegisterSource.source,
                            emailText = regData.emailText,
                            password = regData.passwordText,
                            token = regData.tokenText
                        )
                    )
                }
            )
        }

        composable<Login> {
            val args = it.toRoute<Login>()
            LoginScreen(
                email = args.emailText,
                password = args.passwordText,
                onSignUpClick = {
                    navController.navigate(route = Register) {
                        popUpTo(route = Register) {
                            inclusive = true
                        }
                    }
                },
                onUpButtonPressed = { navController.navigateUp() },
                onForgotPasswordClick = { navController.navigate(ForgotPassword) },
                onLoginClick = {
                    navController.navigate(Home("")) {
                        popUpTo(route = Splash) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable<ForgotPassword> {
            ForgotPasswordScreen(
                navigateToVerification = {
                    navController.navigate(
                        Verification(
                            source = VerificationSource.ForgotPasswordSource.source,
                            emailText = it
                        )
                    )
                },
                onUpButtonPressed = { navController.navigateUp() }
            )
        }

        composable<Verification> {
            val args = it.toRoute<Verification>()
            VerificationScreen(
                email = args.emailText,
                navigateToReset = {
                    if (args.source == VerificationSource.ForgotPasswordSource.source) {
                        navController.navigate(ResetPassword(args.emailText)) {
                            popUpTo(
                                route = Verification(
                                    source = args.source,
                                    emailText = args.emailText,
                                    password = args.password
                                )
                            ) {
                                inclusive = true
                            }
                        }
                    } else {
                        navController.navigate(
                            SetUpAccount(
                                emailText = args.emailText,
                                password = args.password
                            )
                        ) {
                            popUpTo(
                                route = Verification(
                                    source = args.source,
                                    emailText = args.emailText,
                                    password = args.password
                                )
                            ) {
                                inclusive = true
                            }
                        }
                    }
                },
                onUpButtonPressed = { navController.navigateUp() }
            )
        }

        composable<ResetPassword> {
            val args = it.toRoute<ResetPassword>()

            ResetPasswordScreen(
                email = args.emailText,
                onSaveClick = { emailText, passwordText ->
                    navController.navigate(
                        route = Login(
                            emailText = emailText,
                            passwordText = passwordText
                        )
                    ) {
                        popUpTo(route = Login()) {
                            inclusive = true
                        }
                    }
                },
                onUpButtonPressed = { navController.navigateUp() }
            )
        }

        composable<SetUpAccount> {
            val args = it.toRoute<SetUpAccount>()
            val registerResponse = RegData(
                emailText = args.emailText,
                passwordText = args.password,
                confirmPasswordText = args.password,
                tokenText = ""
            )
            SetUpAccountScreen(
                onSaveChangesClick = {
                    navController.navigate(Home("")) {
                        popUpTo(route = Register) {
                            inclusive = true
                        }
                    }
                    /* TODO */
                },
                onUpButtonPressed = { navController.navigateUp() },
                registerResponse = registerResponse
            )
        }

        composable<Home> {
            BottomNavigationBody(
                currentRoute = Home(""),
                content = { HomeMainScreen(
                    onSeeAllClick = { category ->
                        navController.navigate(CategoryProductsList(
                            category = category
                        ))
                    },
                    onSearchIconClick = {
                        navController.navigate(Search)
                    }
                ) },
                navigateToItem = {
                    navController.navigate(it) {
                        popUpTo(route = Home(""))
                    }
                })
        }

        composable<CategoryProductsList> {
            val args = it.toRoute<CategoryProductsList>()

            CategoryProductsScreenContainer(
                title = args.category,
                onUpButtonPressed = { navController.navigateUp() }
            )
        }

        composable<com.example.gronurgrocery.common.presentation.navigation.screens.ProductDetail> {
            ProductDetailScreenContainer(
                onUpButtonPressed = {
                    navController.navigateUp()
                },
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
                        ),
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

        composable<Search> {
            SearchScreenContainer(
                onUpButtonPressed = { navController.navigateUp() }
            )
        }

        composable<Order> {
            BottomNavigationBody(
                currentRoute = Order(""),
                content = {
                    Column(
                        modifier = Modifier
                            .clip(
                                RoundedCornerShape(
                                    bottomStart = 40.dp,
                                    bottomEnd = 40.dp
                                )
                            )
                            .fillMaxSize()
                            .background(Color.Red)
                    ) {

                    }
                },
                navigateToItem = {
                    navController.navigate(it) {
                        popUpTo(route = Home("")) {
                            inclusive = (it == Home(""))
                        }
                    }
                })
        }
        composable<MyCart> {
            BottomNavigationBody(
                currentRoute = MyCart(""),
                content = {
                    Column(
                        modifier = Modifier
                            .clip(
                                RoundedCornerShape(
                                    bottomStart = 40.dp,
                                    bottomEnd = 40.dp
                                )
                            )
                            .fillMaxSize()
                            .background(Color.Green)
                    ) {

                    }
                },
                navigateToItem = {
                    navController.navigate(it) {
                        popUpTo(route = Home("")) {
                            inclusive = (it == Home(""))
                        }
                    }
                })
        }
        composable<More> {
            BottomNavigationBody(
                currentRoute = More(""),
                content = {
                    Column(
                        modifier = Modifier
                            .clip(
                                RoundedCornerShape(
                                    bottomStart = 40.dp,
                                    bottomEnd = 40.dp
                                )
                            )
                            .fillMaxSize()
                            .background(Color.Blue)
                    ) {

                    }
                },
                navigateToItem = {
                    navController.navigate(it) {
                        popUpTo(route = Home("")) {
                            inclusive = (it == Home(""))
                        }
                    }
                })
        }

        composable<ProfileMain> {
            ProfileMain(
                onBackButtonPressed = { navController.navigateUp() },
                onLogoutPressed = {
                    navController.navigate(Login()) {
                        popUpTo(route = Home("")) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable<AccountInfo> {
            AccountInfo(
                onUpButtonPressed = {
                    navController.navigateUp()
                }
            )
        }

        composable<DeliveryAddress> {
            DeliveryAddress(
                onUpButtonPressed = {
                    navController.navigateUp()
                }
            )
        }

        composable<PaymentMethod> {
            PaymentMethod(
                onUpButtonPressed = {
                    navController.navigateUp()
                }
            )
        }
    }
}