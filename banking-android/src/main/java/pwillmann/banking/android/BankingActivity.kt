package pwillmann.banking.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import pwillmann.banking.android.theme.BankingTheme
import pwillmann.banking.android.ui.home.Home

class BankingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BankingTheme {
                Home()
            }
        }
    }
}
