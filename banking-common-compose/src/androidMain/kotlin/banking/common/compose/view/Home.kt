package banking.common.compose.view

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import banking.common.compose.ui.home.Transactions
import banking.common.compose.ui.theme.BankingTheme
import banking.common.data.repository.AccountRepository

@Composable
fun Home() {
    val repo = remember { AccountRepository.instance }
    val accounts = remember(repo) { repo.getAllAccounts() }
    val selectedAccount = remember(accounts) { mutableStateOf(accounts.first()) }
    BankingTheme {
        Surface(
            color = MaterialTheme.colors.background,
            contentColor = MaterialTheme.colors.onBackground,
        ) {
            ScrollableColumn {
                HomeHeader(
                    accounts = accounts,
                    onAccountSelected = {
                        selectedAccount.value = it
                    }
                )
                Transactions(transactions = selectedAccount.value.lastTransactions)
            }
        }
    }
}
