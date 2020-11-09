package banking.common.data.repository

import banking.common.data.model.Account
import banking.common.data.model.AccountHistoryItem
import banking.common.data.model.AccountTransaction
import banking.common.data.model.AccountType
import banking.common.data.model.TransactionType
import banking.common.data.util.DateProgression
import banking.common.data.util.Random
import kotlin.math.roundToInt
import kotlin.math.sin
import kotlin.time.ExperimentalTime
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayAt

@OptIn(ExperimentalTime::class)
class AccountRepository {

    companion object {
        val instance = AccountRepository()
    }

    private var accounts: List<Account> = listOf()

    fun getAllAccounts(): List<Account> {
        if (accounts.isEmpty()) {
            setupAccounts()
        }
        return accounts
    }

    fun getAccountById(id: String): Account? {
        return accounts.find { it.id == id }
    }

    fun getNewAccount(): Account {
        return generateAccount()
    }

    fun setupAccounts() {
        accounts = listOf(generateAccount(), generateAccount(), generateAccount())
    }

    private fun generateAccount(): Account {
        val balance = Random.double() * 9876
        return Account(
            id = Random.string(),
            accountType = getRandomAccountType(),
            currentBalance = balance,
            lastTransactions = generateTransactions(
                numberOfTransactions = 60,
                TransactionType.values().toList()
            ),
            accountHistory = generateAccountHistory(balance)
        )
    }

    private fun getRandomAccountType(): AccountType {
        return if (Random.double() > 0.5) {
            AccountType.CREDIT
        } else {
            AccountType.DEBIT
        }
    }

    private fun generateAccountHistory(baseBalance: Double): List<AccountHistoryItem> {
        val balance = baseBalance.coerceIn(1000.0, 9999.9)
        val history = mutableListOf<AccountHistoryItem>()
        val now = Clock.System.todayAt(TimeZone.currentSystemDefault())
        val startDate = now.plus(-6, DateTimeUnit.MONTH)
        val rate = Random.double()
        for ((index, date) in (startDate..now).withIndex()) {
            val historyItem =
                AccountHistoryItem(date = date, balance = getBalanceForDay(index, balance, rate))
            history.add(historyItem)
        }
        return history.toList()
    }

    private fun generateTransactions(
        numberOfTransactions: Int,
        allowedTransactionTypes: List<TransactionType>
    ): List<AccountTransaction> {
        var transactions = mutableListOf<AccountTransaction>()

        for (i in 0..numberOfTransactions) {
            val transactionType =
                allowedTransactionTypes[(Random.double() * allowedTransactionTypes.size * 2).roundToInt() % allowedTransactionTypes.size]

            val maxDayOffset = (i % numberOfTransactions / 3).coerceIn(0, 30)
            val transaction = when (transactionType) {
                TransactionType.BANK_TRANSFER -> AccountTransaction.BankTransferTransaction.generateItem(
                    getRandomDateTime(maxDayOffset)
                )
                TransactionType.WITHDRAWAL -> AccountTransaction.WithdrawalTransaction.generateItem(
                    getRandomDateTime(maxDayOffset)
                )
                TransactionType.DEPOSIT -> AccountTransaction.DepositTransaction.generateItem(
                    getRandomDateTime(maxDayOffset)
                )
                TransactionType.CHARGE -> AccountTransaction.ChargeTransaction.generateItem(
                    getRandomDateTime(maxDayOffset)
                )
            }
            transactions.add(transaction)
        }

        return transactions.toList().sortedByDescending { it.date }
    }

    private fun getBalanceForDay(n: Int, baseBalance: Double, rate: Double): Double {
        return 166 * sin(n.toDouble() / 25) + (rate * n) + baseBalance
    }

    @ExperimentalTime
    private fun getRandomDateTime(
        maxDayOffset: Int = 90,
        specificDayOffset: Int? = null
    ): LocalDateTime {
        val dayOffset = specificDayOffset ?: maxDayOffset
        val currentMoment: Instant = Clock.System.now()
        val systemTZ = TimeZone.currentSystemDefault()

        val randomMoment = currentMoment
            .plus(-(Random.double() * dayOffset).roundToInt(), DateTimeUnit.DAY, systemTZ)
            .plus((Random.double() * 24).roundToInt(), DateTimeUnit.HOUR, systemTZ)
            .plus((Random.double() * 60).roundToInt(), DateTimeUnit.MINUTE, systemTZ)

        return randomMoment.toLocalDateTime(systemTZ)
    }
}

operator fun LocalDate.rangeTo(other: LocalDate) = DateProgression(this, other)
