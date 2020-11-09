package banking.common.compose.platform

import androidx.compose.desktop.DesktopTheme
import androidx.compose.runtime.Composable

@Composable
actual fun PlatformTheme(content: @Composable () -> Unit) = DesktopTheme(content = content)
