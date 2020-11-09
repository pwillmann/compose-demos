package banking.common.compose.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import banking.common.compose.platform.PlatformTheme

private val bankingColorPalette = lightColors(
    primary = purpleDark,
    primaryVariant = purpleLight,
    secondary = blue,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = greyDark,
    onSurface = greyDark,
    /* Other default colors to override
background = Color.White,
surface = Color.White,
onPrimary = Color.White,
onSecondary = Color.Black,
onBackground = Color.Black,
onSurface = Color.Black,
*/
)

@Composable
fun BankingTheme(content: @Composable() () -> Unit) {
    MaterialTheme(
        colors = bankingColorPalette,
        typography = bankingTypography(),
        shapes = bankingShapes,
    ) {
        PlatformTheme(content = content)
    }
}
