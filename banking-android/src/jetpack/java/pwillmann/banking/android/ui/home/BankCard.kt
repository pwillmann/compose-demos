package pwillmann.banking.android.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.ChainStyle
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.Dimension
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AmbientContentAlpha
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawOpacity
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import banking.common.data.model.Account
import banking.common.data.model.AccountType
import com.pwillmann.composedemos.banking.util.print
import pwillmann.banking.android.R
import pwillmann.banking.android.theme.BankingFonts
import pwillmann.banking.android.theme.ThemeOpacity
import pwillmann.banking.android.theme.blue
import pwillmann.banking.android.theme.purpleLight

@Composable
fun CardNumberBlock(number: String? = null, modifier: Modifier = Modifier) {
    Text(
        number?.substring(0..3) ?: "••••",
        style = MaterialTheme.typography.subtitle1,
        fontFamily = BankingFonts.ocr,
        modifier = modifier
    )
}

@Composable
fun BankCard(account: Account, modifier: Modifier = Modifier) {
    val backgroundColor = when (account.accountType) {
        AccountType.DEBIT -> purpleLight
        AccountType.CREDIT -> blue
    }
    Card(
        backgroundColor = backgroundColor,
        contentColor = Color.White,
        elevation = 8.dp,
        shape = MaterialTheme.shapes.medium,
        modifier = modifier.fillMaxWidth(0.75f).aspectRatio(1.586f),
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            val (
                balanceLabel, balance, visa,
                cardNumberFirstBlock,
                cardNumberSecondBlock,
                cardNumberThirdBlock,
                cardNumberLastBlock,
                cardType
            ) = createRefs()
            Providers(AmbientContentAlpha provides ContentAlpha.medium) {
                Text(
                    "Current Balance",
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.constrainAs(balanceLabel) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
                )
            }
            Text(
                account.currentBalance.print(),
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(balance) {
                    top.linkTo(balanceLabel.bottom)
                    start.linkTo(parent.start)
                }
            )
            Image(
                asset = vectorResource(R.drawable.ic_visa),
                contentScale = ContentScale.Inside,
                modifier = Modifier.constrainAs(visa) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    height = Dimension.value(16.dp)
                    width = Dimension.wrapContent
                }
            )
            CardNumberBlock(
                modifier = Modifier.constrainAs(cardNumberFirstBlock) {
                    linkTo(top = parent.top, bottom = parent.bottom, bias = 0.65f)
                    start.linkTo(parent.start)
                    width = Dimension.preferredWrapContent
                }
            )
            CardNumberBlock(
                modifier = Modifier.constrainAs(cardNumberSecondBlock) {
                    centerVerticallyTo(cardNumberFirstBlock)
                    width = Dimension.preferredWrapContent
                }
            )
            CardNumberBlock(
                modifier = Modifier.constrainAs(cardNumberThirdBlock) {
                    centerVerticallyTo(cardNumberFirstBlock)
                    width = Dimension.preferredWrapContent
                }
            )
            CardNumberBlock(
                account.lastFourCardDigits,
                modifier = Modifier.constrainAs(cardNumberLastBlock) {
                    centerVerticallyTo(cardNumberFirstBlock)
                    end.linkTo(parent.end)
                    width = Dimension.preferredWrapContent
                }
            )
            createHorizontalChain(
                cardNumberFirstBlock,
                cardNumberSecondBlock,
                cardNumberThirdBlock,
                cardNumberLastBlock,
                chainStyle = ChainStyle.SpreadInside
            )

            Text(
                account.accountType.printString(),
                style = MaterialTheme.typography.body2,
                fontFamily = BankingFonts.ocr,
                modifier = Modifier.constrainAs(cardType) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }.drawOpacity(ThemeOpacity.high)
            )
        }
    }
}

private fun AccountType.printString(): String {
    return when (this) {
        AccountType.DEBIT -> "Debit Card"
        AccountType.CREDIT -> "Credit Card"
    }
}
