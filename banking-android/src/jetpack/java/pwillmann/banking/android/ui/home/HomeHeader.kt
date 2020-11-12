package pwillmann.banking.android.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.AmbientContentAlpha
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.platform.AnimationClockAmbient
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import banking.common.data.model.Account
import pwillmann.banking.android.theme.ThemeOpacity
import pwillmann.banking.android.ui.common.Pager
import pwillmann.banking.android.ui.common.PagerState
import timber.log.Timber

@Composable
fun HomeHeader(
    accounts: List<Account>,
    onAccountSelected: (Account) -> Unit = {}
) {
    Surface(
        color = MaterialTheme.colors.primary,
        contentColor = contentColorFor(color = MaterialTheme.colors.primary)
    ) {
        Column(modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(16.dp)) {
            Text(
                "My Cards",
                style = MaterialTheme.typography.h6
            )
            Spacer(modifier = Modifier.height(16.dp))
            CardPager(
                accounts,
                onAccountSelected = onAccountSelected,
                modifier = Modifier.wrapContentHeight()
            )
            Spacer(modifier = Modifier.height(16.dp))
            AccountActions(modifier = Modifier.fillMaxWidth())
        }
    }
}

@Preview
@Composable
fun AccountActions(modifier: Modifier = Modifier) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {
        AccountAction(icon = Icons.Default.KeyboardArrowUp, text = "Top Up")
        AccountAction(icon = Icons.Default.Lock, text = "Block Card")
        AccountAction(icon = Icons.Default.Settings, text = "Card Settings")
    }
}

@Composable
fun AccountAction(icon: VectorAsset, text: String, onClick: () -> Unit = {}) {
    Surface(
        shape = MaterialTheme.shapes.small,
        color = Color.Transparent,
        contentColor = MaterialTheme.colors.onPrimary.copy(alpha = ThemeOpacity.high),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable(onClick = onClick)
        ) {
            Icon(
                icon,
                modifier = Modifier.height(32.dp)
                    .wrapContentWidth()
                    .background(
                        MaterialTheme.colors.onPrimary.copy(alpha = ThemeOpacity.low),
                        shape = MaterialTheme.shapes.small
                    )
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Providers(AmbientContentAlpha provides ContentAlpha.medium) {
                Text(
                    text,
                    style = MaterialTheme.typography.caption,
                )
            }
            Spacer(modifier = Modifier.width(4.dp))
        }
    }
}

@Composable
fun CardPager(
    items: List<Account>,
    pagerState: PagerState = run {
        val clock = AnimationClockAmbient.current
        remember(clock) { PagerState(clock) }
    },
    onAccountSelected: (Account) -> Unit = {},
    modifier: Modifier = Modifier
) {
    pagerState.maxPage = (items.size - 1).coerceAtLeast(0)
    pagerState.onPageSelected = {
        Timber.d("CardPager onPageSelected $it")
        onAccountSelected(items[it])
    }

    Pager(
        state = pagerState,
        itemSpacing = 24.dp,
        modifier = modifier
    ) { padding ->
        BankCard(
            account = items[page],
            modifier = Modifier.padding(padding)
        )
    }
}
