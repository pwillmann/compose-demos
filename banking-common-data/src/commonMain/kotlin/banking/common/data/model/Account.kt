package banking.common.data.model

data class Account(
    val id: String = "123",
    val accountType: AccountType = AccountType.DEBIT,
    val currentBalance: Double = 2354.54,
    val lastFourCardDigits: String = "1234",
    val lastTransactions: List<AccountTransaction> = listOf(),
    val accountHistory: List<AccountHistoryItem> = listOf()
)
