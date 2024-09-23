package com.example.gronurgrocery.common

object Constants {

    const val CLICK_EFFECT_DURATION = 1000L
    const val BASE_URL = "https://gronurgrocery.azurewebsites.net/api/"
    const val VERIFICATION_FAIL = "fail"
}


/**
 * package com.example.gronurgrocery.features.auth.presentation
 *
 * import androidx.compose.foundation.layout.Box
 * import androidx.compose.foundation.layout.fillMaxSize
 * import androidx.compose.material3.Text
 * import androidx.compose.runtime.Composable
 * import androidx.compose.ui.Alignment
 * import androidx.compose.ui.Modifier
 * import androidx.compose.ui.tooling.preview.Preview
 * import com.example.gronurgrocery.features.ui.theme.GronurGroceryTheme
 *
 * @Composable
 * fun VerificationScreen(modifier: Modifier = Modifier) {
 *     Box(
 *         contentAlignment = Alignment.Center,
 *         modifier = modifier.fillMaxSize()
 *     ) {
 *         Text(text = "Hello From Verification")
 *     }
 * }
 *
 * @Preview
 * @Composable
 * private fun PreviewVerificationScreen() {
 *     GronurGroceryTheme {
 *         VerificationScreen(
 *
 *         )
 *     }
 * }
 */