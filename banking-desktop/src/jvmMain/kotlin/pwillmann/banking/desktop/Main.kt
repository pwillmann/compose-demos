package pwillmann.banking.desktop

import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.ExperimentalLayout
import androidx.compose.ui.unit.IntSize
import banking.common.compose.view.Home
import javax.swing.SwingUtilities.invokeLater

@OptIn(ExperimentalLayout::class)
fun main() {
    invokeLater {
        Window(
            title = "Banking",
            size = IntSize(768, 1792),
        ) {
            Home()
        }
    }
}
