package banking.common.data.model

import banking.common.data.util.Random
import kotlinx.datetime.LocalDateTime

sealed class AccountTransaction(
    val type: TransactionType,
    val amount: Double,
    val date: LocalDateTime
) {
    data class BankTransferTransaction(
        val transactionAmount: Double,
        val from: String? = null,
        val to: String? = null,
        val transactionDate: LocalDateTime
    ) : AccountTransaction(type = TransactionType.BANK_TRANSFER, amount = transactionAmount, date = transactionDate) {

        companion object {
            fun generateItem(date: LocalDateTime): BankTransferTransaction {
                val amount = (0.5 - Random.double()) * 6243

                return BankTransferTransaction(
                    transactionAmount = amount,
                    from = if (amount >= 0) "Agent Smith" else null,
                    to = if (amount >= 0) null else "Neo",
                    transactionDate = date
                )
            }
        }
    }

    data class WithdrawalTransaction(
        val transactionAmount: Double,
        val transactionDate: LocalDateTime
    ) : AccountTransaction(type = TransactionType.WITHDRAWAL, amount = transactionAmount, date = transactionDate) {

        companion object {
            fun generateItem(date: LocalDateTime): WithdrawalTransaction {
                val amount = Random.double() * 6243

                return WithdrawalTransaction(
                    transactionAmount = amount,
                    transactionDate = date
                )
            }
        }
    }

    data class DepositTransaction(
        val transactionAmount: Double,
        val transactionDate: LocalDateTime
    ) : AccountTransaction(type = TransactionType.DEPOSIT, amount = transactionAmount, date = transactionDate) {

        companion object {
            fun generateItem(date: LocalDateTime): WithdrawalTransaction {
                val amount = Random.double() * 6243

                return WithdrawalTransaction(
                    transactionAmount = amount,
                    transactionDate = date
                )
            }
        }
    }

    data class ChargeTransaction(
        val transactionAmount: Double,
        val transactionDate: LocalDateTime,
        val by: String? = null,
    ) : AccountTransaction(type = TransactionType.CHARGE, amount = transactionAmount, date = transactionDate) {

        companion object {
            fun generateItem(date: LocalDateTime): ChargeTransaction {
                val amount = Random.double() * 6243

                return ChargeTransaction(
                    transactionAmount = amount,
                    by = "Keanu Reeves",
                    transactionDate = date
                )
            }
        }
    }
}
