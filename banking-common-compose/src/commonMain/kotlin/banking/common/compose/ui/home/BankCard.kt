package banking.common.compose.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.AmbientEmphasisLevels
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideEmphasis
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawOpacity
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import banking.common.compose.ui.theme.BankingFonts
import banking.common.compose.ui.theme.ThemeOpacity
import banking.common.compose.ui.theme.blue
import banking.common.compose.ui.theme.purpleLight
import banking.common.compose.util.print
import banking.common.data.model.Account
import banking.common.data.model.AccountType

@Composable
fun CardNumberBlock(number: String? = null, modifier: Modifier = Modifier) {
    Text(
        number?.substring(0..3) ?: "••••",
        style = MaterialTheme.typography.subtitle1,
        fontFamily = BankingFonts.ocr(),
        modifier = modifier
    )
}

@Composable
fun CardBalance(balance: Double, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        ProvideEmphasis(emphasis = AmbientEmphasisLevels.current.medium) {
            Text(
                "Current Balance",
                style = MaterialTheme.typography.caption,
                modifier = Modifier
            )
        }
        Text(
            "$ ${balance.print()}",
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
        )
    }
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
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            CardBalance(
                account.currentBalance,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(5f)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .weight(2f)
            ) {
                CardNumberBlock(
                    modifier = Modifier.wrapContentSize()
                )
                CardNumberBlock(
                    modifier = Modifier.wrapContentSize()
                )
                CardNumberBlock(
                    modifier = Modifier.wrapContentSize()
                )
                CardNumberBlock(
                    account.lastFourCardDigits,
                    modifier = Modifier.wrapContentSize()
                )
            }
            Box(
                modifier = Modifier
                    .wrapContentWidth()
                    .weight(2f)
            ) {
                Text(
                    account.accountType.printString(),
                    style = MaterialTheme.typography.body2,
                    fontFamily = BankingFonts.ocr(),
                    modifier = Modifier
                        .drawOpacity(ThemeOpacity.high)
                        .align(Alignment.BottomStart)
                )
            }
        }
    }
}

private fun AccountType.printString(): String {
    return when (this) {
        AccountType.DEBIT -> "Debit Card"
        AccountType.CREDIT -> "Credit Card"
    }
}
