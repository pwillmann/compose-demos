package pwillmann.banking.android.ui.legacy

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.Dimension
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.AmbientEmphasisLevels
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideEmphasis
import androidx.compose.material.Surface
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import banking.common.data.model.AccountTransaction
import com.pwillmann.composedemos.banking.util.printNoDecimals
import java.time.LocalDateTime
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toLocalDateTime
import pwillmann.banking.android.R
import pwillmann.banking.android.theme.BankingTheme
import pwillmann.banking.android.theme.ThemeOpacity
import pwillmann.banking.android.theme.blue
import pwillmann.banking.android.theme.green
import pwillmann.banking.android.theme.purpleLight
import pwillmann.banking.android.ui.common.util.formatDateOfYear
import pwillmann.banking.android.ui.common.util.printTime

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
                    val localDate = transaction.date.toJavaLocalDateTime()
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
                            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
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
        ConstraintLayout(modifier = Modifier.fillMaxWidth().wrapContentHeight()) {
            val (icon, title, subtitle, amount, time) = createRefs()
            TransactionIcon(
                transaction,
                modifier = Modifier.constrainAs(icon) {
                    start.linkTo(parent.start, margin = 8.dp)
                    linkTo(parent.top, parent.bottom, bottomMargin = 8.dp, topMargin = 8.dp)
                    height = Dimension.value(48.dp)
                    width = Dimension.value(48.dp)
                }
            )
            Text(
                transaction.type.print(),
                style = MaterialTheme.typography.subtitle2,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(title) {
                    start.linkTo(icon.end, margin = 8.dp)
                    top.linkTo(parent.top, margin = 8.dp)
                }
            )
            ProvideEmphasis(emphasis = AmbientEmphasisLevels.current.medium) {
                Subtitle(
                    transaction,
                    modifier = Modifier.constrainAs(subtitle) {
                        start.linkTo(title.start)
                        top.linkTo(title.bottom)
                    }
                )
            }
            Text(
                transaction.amount.printNoDecimals(),
                style = MaterialTheme.typography.subtitle2,
                fontWeight = FontWeight.Bold,
                color = getColorForAmount(transaction.amount),
                modifier = Modifier.constrainAs(amount) {
                    end.linkTo(parent.end, margin = 16.dp)
                    top.linkTo(title.top)
                }
            )
            ProvideEmphasis(emphasis = AmbientEmphasisLevels.current.medium) {
                Text(
                    transaction.date.toJavaLocalDateTime().printTime(),
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.constrainAs(time) {
                        end.linkTo(amount.end)
                        top.linkTo(title.bottom)
                    }
                )
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
    Text(
        text,
        style = MaterialTheme.typography.caption,
        modifier = modifier
    )
}

@Composable
private fun TransactionIcon(transaction: AccountTransaction, modifier: Modifier = Modifier) {
    var icon = vectorResource(R.drawable.ic_withdrawal)
    var color = MaterialTheme.colors.primaryVariant
    when (transaction) {
        is AccountTransaction.BankTransferTransaction -> {
            icon = vectorResource(R.drawable.ic_transfer)
            color = purpleLight
        }
        is AccountTransaction.WithdrawalTransaction -> {
            icon = vectorResource(R.drawable.ic_withdrawal)
            color = blue
        }
        is AccountTransaction.DepositTransaction -> {
            icon = vectorResource(R.drawable.ic_deposit)
            color = Color.Yellow
        }
        is AccountTransaction.ChargeTransaction -> {
            icon = vectorResource(R.drawable.ic_transfer)
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

@ExperimentalAnimationApi
@Preview
@Composable
fun BankTransactionPreview() {
    val transaction = AccountTransaction
        .BankTransferTransaction
        .generateItem(Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()))
    BankingTheme {
        AccountTransaction(transaction = transaction)
    }
}
