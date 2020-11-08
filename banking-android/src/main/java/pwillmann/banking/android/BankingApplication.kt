package pwillmann.banking.android

import android.app.Application
import timber.log.Timber
import timber.log.Timber.DebugTree

class BankingApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}
