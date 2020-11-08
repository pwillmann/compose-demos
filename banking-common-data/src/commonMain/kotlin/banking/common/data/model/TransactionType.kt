package banking.common.data.model

enum class TransactionType {
    BANK_TRANSFER, WITHDRAWAL, DEPOSIT, CHARGE;

    fun print(): String {
        return when (this) {
            BANK_TRANSFER -> "Bank transfer"
            WITHDRAWAL -> "Cash withdrawal"
            DEPOSIT -> "Cash deposit"
            CHARGE -> "Charge"
        }
    }
}
