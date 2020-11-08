package banking.common.data.model

import kotlinx.datetime.LocalDate

data class AccountHistoryItem(
    val date: LocalDate,
    val balance: Double
)
