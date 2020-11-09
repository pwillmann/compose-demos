package banking.common.compose.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.AmbientEmphasisLevels
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideEmphasis
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import banking.common.compose.ui.assets.vector.Deposit
import banking.common.compose.ui.assets.vector.Transfer
import banking.common.compose.ui.assets.vector.VectorAssets
import banking.common.compose.ui.assets.vector.Withdrawal
import banking.common.compose.ui.theme.ThemeOpacity
import banking.common.compose.ui.theme.blue
import banking.common.compose.ui.theme.green
import banking.common.compose.ui.theme.purpleLight
import banking.common.compose.util.printNoDecimals
import banking.common.compose.util.printTime
import banking.common.data.model.AccountTransaction
import formatDateOfYear
import kotlinx.datetime.LocalDateTime

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Transactions(transactions: List<AccountTransaction>) {
    Surface(
        color = MaterialTheme.colors.background,
        contentColor = contentColorFor(color = MaterialTheme.colors.background),
        modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(vertical = 16.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                "Transactions",
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Column {
                var previousDate: LocalDateTime? = null
                for ((index, transaction) in transactions.withIndex()) {
                    val localDate = transaction.date
                    val animationDelay = index.coerceAtMost(20) * 80 + 300
                    if (previousDate == null || localDate.dayOfYear != previousDate.dayOfYear) {
                        AnimatedVisibility(
                            visible = true,
                            enter = fadeIn(
                                // Overwrites the initial value of alpha to 0.4f for fade in, 0 by default
                                initialAlpha = 0.0f,
                                animSpec = tween(delayMillis = animationDelay)

                            ),
                            initiallyVisible = false
                        ) {
                            ProvideEmphasis(emphasis = AmbientEmphasisLevels.current.medium) {
                                Text(
                                    formatDateOfYear(localDate),
                                    style = MaterialTheme.typography.subtitle1,
                                    fontWeight = FontWeight.Medium,
                                    modifier = Modifier.padding(
                                        vertical = 4.dp,
                                        horizontal = 16.dp,
                                    )
                                )
                            }
                        }
                    }
                    AnimatedVisibility(
                        visible = true,
                        enter = fadeIn(
                            // Overwrites the initial value of alpha to 0.4f for fade in, 0 by default
                            initialAlpha = 0.0f,
                            animSpec = tween(delayMillis = animationDelay)

                        ),
                        initiallyVisible = false
                    ) {
                        AccountTransaction(
                            transaction,
                            modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp)
                        )
                    }
                    previousDate = localDate
                }
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun AccountTransaction(
    transaction: AccountTransaction,
    modifier: Modifier = Modifier,
) {
    Card(
        elevation = 8.dp,
        shape = MaterialTheme.shapes.large,
        modifier = modifier.fillMaxWidth().wrapContentHeight()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier.fillMaxWidth().wrapContentHeight()
        ) {
            TransactionIcon(
                transaction,
                modifier = Modifier.width(48.dp).height(48.dp)
            )
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxHeight().padding(start = 8.dp).weight(1f)
            ) {
                Text(
                    transaction.type.print(),
                    style = MaterialTheme.typography.subtitle2,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )
                ProvideEmphasis(emphasis = AmbientEmphasisLevels.current.medium) {
                    Subtitle(transaction, modifier = Modifier.fillMaxWidth())
                }
            }
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxHeight()
            ) {
                Text(
                    "$ ${transaction.amount.printNoDecimals()}",
                    style = MaterialTheme.typography.subtitle2,
                    fontWeight = FontWeight.Bold,
                    color = getColorForAmount(transaction.amount),
                    modifier = Modifier
                )
                ProvideEmphasis(emphasis = AmbientEmphasisLevels.current.medium) {
                    Text(
                        transaction.date.printTime(),
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier
                    )
                }
            }
        }
    }
}

@Composable
private fun Subtitle(transaction: AccountTransaction, modifier: Modifier = Modifier) {
    val text = remember(transaction) {
        when (transaction) {
            is AccountTransaction.BankTransferTransaction -> {
                if (transaction.from.isNullOrBlank()) {
                    "to ${transaction.to}"
                } else {
                    "from ${transaction.from}"
                }
            }
            is AccountTransaction.WithdrawalTransaction -> {
                ""
            }
            is AccountTransaction.DepositTransaction -> {
                ""
            }
            is AccountTransaction.ChargeTransaction -> {
                "by ${transaction.by}"
            }
        }
    }
    if (text.isEmpty()) return
    Text(
        text,
        style = MaterialTheme.typography.caption,
        modifier = modifier
    )
}

@Composable
private fun TransactionIcon(transaction: AccountTransaction, modifier: Modifier = Modifier) {
    var icon = VectorAssets.Withdrawal
    var color = MaterialTheme.colors.primaryVariant
    when (transaction) {
        is AccountTransaction.BankTransferTransaction -> {
            icon = VectorAssets.Transfer
            color = purpleLight
        }
        is AccountTransaction.WithdrawalTransaction -> {
            icon = VectorAssets.Withdrawal
            color = blue
        }
        is AccountTransaction.DepositTransaction -> {
            icon = VectorAssets.Deposit
            color = Color.Yellow
        }
        is AccountTransaction.ChargeTransaction -> {
            icon = VectorAssets.Transfer
            color = Color.Red
        }
    }

    Image(
        asset = icon,
        contentScale = ContentScale.Inside,
        colorFilter = ColorFilter.tint(color),
        modifier = modifier
            .background(
                color.copy(alpha = ThemeOpacity.low),
                shape = MaterialTheme.shapes.small.copy(bottomRight = CornerSize(0.dp))
            ).padding(8.dp)

    )
}

private fun getColorForAmount(amount: Double): Color {
    return when {
        amount > 0 -> {
            green
        }
        amount == 0.0 -> {
            Color.Black
        }
        else -> {
            Color.Red
        }
    }
}
