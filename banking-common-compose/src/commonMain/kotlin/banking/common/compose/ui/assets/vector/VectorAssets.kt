package banking.common.compose.ui.assets.vector

import androidx.compose.ui.graphics.vector.VectorAsset
import kotlin.collections.List

public object VectorAssets

private var _allIcons: List<VectorAsset>? = null

public val VectorAssets.AllIcons: List<VectorAsset>
    get() {
        if (_allIcons != null) {
            return _allIcons!!
        }
        _allIcons = listOf(Visa, Withdrawal, Deposit, Transfer)
        return _allIcons!!
    }
