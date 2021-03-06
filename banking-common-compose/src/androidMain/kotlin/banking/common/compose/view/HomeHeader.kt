package banking.common.compose.view

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
import androidx.compose.material.AmbientEmphasisLevels
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideEmphasis
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.platform.AnimationClockAmbient
import androidx.compose.ui.unit.dp
import banking.common.compose.ui.common.Pager
import banking.common.compose.ui.common.PagerState
import banking.common.compose.ui.home.BankCard
import banking.common.compose.ui.theme.ThemeOpacity
import banking.common.data.model.Account

@Composable
fun HomeHeader(
    accounts: List<Account>,
    onAccountSelected: (Account) -> Unit = {}
) {
    Surface(
        color = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary
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
            ProvideEmphasis(emphasis = AmbientEmphasisLevels.current.medium) {
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
