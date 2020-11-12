package pwillmann.banking.android.ui.home

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.viewinterop.viewModel
import androidx.ui.tooling.preview.Preview
import banking.common.data.repository.AccountRepository
import kotlin.time.ExperimentalTime
import pwillmann.banking.android.theme.BankingTheme

@OptIn(ExperimentalTime::class)
@Preview
@Composable
fun HomePreview() {
    val repo = remember { AccountRepository.instance }
    val accounts = remember(repo) { repo.getAllAccounts() }
    val selectedAccount = remember(accounts) { mutableStateOf(accounts.first()) }
    BankingTheme {
        Surface(
            color = MaterialTheme.colors.background,
            contentColor = contentColorFor(color = MaterialTheme.colors.background),
        ) {
            Column {
                HomeHeader(
                    accounts = accounts,
                    onAccountSelected = {
                        selectedAccount.value = it
                    }
                )
                AccountHistory(account = selectedAccount.value)
                Transactions(transactions = selectedAccount.value.lastTransactions)
            }
        }
    }
}

@Composable
fun Home(homeViewModel: HomeViewModel = viewModel()) {
    val selectedAccount = homeViewModel.selectedAccount.observeAsState()
    val accounts = homeViewModel.accounts.observeAsState()

    Surface(
        color = MaterialTheme.colors.background,
        contentColor = contentColorFor(color = MaterialTheme.colors.background),
    ) {
        if (accounts.value != null) {
            ScrollableColumn {
                HomeHeader(
                    accounts = accounts.value!!,
                    onAccountSelected = {
                        homeViewModel.selectAccount(it)
                    }
                )
                AccountHistory(account = selectedAccount.value!!)
                Transactions(transactions = selectedAccount.value!!.lastTransactions)
            }
        }
    }
}
