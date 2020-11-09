package pwillmann.banking.android.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import banking.common.data.model.Account
import banking.common.data.repository.AccountRepository
import timber.log.Timber

class HomeViewModel constructor(
    private val accountRepo: AccountRepository = AccountRepository(),
) : ViewModel() {

    private val accountsLiveData: MutableLiveData<List<Account>> by lazy { MutableLiveData<List<Account>>() }
    private val selectedAccountLiveData: MutableLiveData<Account> by lazy { MutableLiveData<Account>() }

    init {
        fetchAccounts()
    }

    val accounts: LiveData<List<Account>> = accountsLiveData
    val selectedAccount: LiveData<Account> = selectedAccountLiveData

    private fun fetchAccounts() {
        val accountList = accountRepo.getAllAccounts()
        accountsLiveData.value = accountList
        selectAccount(accountList.first())
    }

    fun selectAccount(account: Account) {
        Timber.d("selectAccount($account)")
        selectedAccountLiveData.value = account
    }
}
