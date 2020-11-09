package banking.common.compose.ui.theme

import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.fontFamily
import androidx.compose.ui.unit.sp
import banking.common.compose.platform.font

// Set of Material typography styles to start with
@Composable
fun bankingTypography() = Typography(
    defaultFontFamily = BankingFonts.montserrat(),
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
button = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.W500,
    fontSize = 14.sp
),
caption = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp
)
*/
)

object BankingFonts {
    @Composable
    fun montserrat() = fontFamily(
        montserratBold(), montserratNormal()
    )
    @Composable
    fun ocr() = fontFamily(ocrNormal())
}

@Composable
private fun ocrNormal() = font(
    name = "OCR",
    res = "ocra_regular",
    weight = FontWeight.Normal,
    style = FontStyle.Normal
)
@Composable
private fun montserratBold() = font(
    name = "Montserrat Bold",
    res = "montserrat_bold",
    weight = FontWeight.Bold,
    style = FontStyle.Normal
)
@Composable
private fun montserratNormal() = font(
    name = "Montserrat Normal",
    res = "montserrat_regular",
    weight = FontWeight.Normal,
    style = FontStyle.Normal
)
