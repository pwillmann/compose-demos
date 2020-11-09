package banking.common.compose.ui.common

import androidx.compose.animation.AnimatedFloatModel
import androidx.compose.animation.core.AnimationClockObservable
import androidx.compose.animation.core.AnimationEndReason
import androidx.compose.animation.core.fling
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.gesture.scrollorientationlocking.Orientation
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.ParentDataModifier
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEachIndexed
import kotlin.math.max
import kotlin.math.roundToInt

/**
 * This is a modified version of:
 * https://gist.github.com/adamp/07d468f4bcfe632670f305ce3734f511
 */

class PagerState(
    clock: AnimationClockObservable,
    currentPage: Int = 0,
    minPage: Int = 0,
    maxPage: Int = 0,
    onPageSelected: (Int) -> Unit = {}
) {
    private var _onPageSelected by mutableStateOf(onPageSelected)
    var onPageSelected: (Int) -> Unit
        get() = _onPageSelected
        set(value) {
            _onPageSelected = value
        }

    private var _minPage by mutableStateOf(minPage)
    var minPage: Int
        get() = _minPage
        set(value) {
            _minPage = value.coerceAtMost(_maxPage)
            _currentPage = _currentPage.coerceIn(_minPage, _maxPage)
        }

    private var _maxPage by mutableStateOf(maxPage, structuralEqualityPolicy())
    var maxPage: Int
        get() = _maxPage
        set(value) {
            _maxPage = value.coerceAtLeast(_minPage)
            _currentPage = _currentPage.coerceIn(_minPage, maxPage)
        }

    private var _currentPage by mutableStateOf(currentPage.coerceIn(minPage, maxPage))
    var currentPage: Int
        get() = _currentPage
        set(value) {
            val newCurrentPage = value.coerceIn(minPage, maxPage)
            if (_currentPage != newCurrentPage) {
                onPageSelected(newCurrentPage)
            }
            _currentPage = newCurrentPage
        }

    enum class SelectionState { Selected, Undecided }

    var selectionState by mutableStateOf(SelectionState.Selected)

    inline fun <R> selectPage(block: PagerState.() -> R): R = try {
        selectionState = SelectionState.Undecided
        block()
    } finally {
        selectPage()
    }

    fun selectPage() {
        currentPage -= currentPageOffset.roundToInt()
        currentPageOffset = 0f
        selectionState = SelectionState.Selected
    }

    private var _currentPageOffset = AnimatedFloatModel(0f, clock = clock).apply {
        setBounds(-1f, 1f)
    }
    var currentPageOffset: Float
        get() = _currentPageOffset.value
        set(value) {
            val max = if (currentPage == minPage) 0f else 1f
            val min = if (currentPage == maxPage) 0f else -1f
            _currentPageOffset.snapTo(value.coerceIn(min, max))
        }

    fun fling(velocity: Float) {
        if (velocity < 0 && currentPage == maxPage) return
        if (velocity > 0 && currentPage == minPage) return

        _currentPageOffset.fling(velocity) { reason, _, _ ->
            if (reason != AnimationEndReason.Interrupted) {
                _currentPageOffset.animateTo(currentPageOffset.roundToInt().toFloat()) { _, _ ->
                    selectPage()
                }
            }
        }
    }

    override fun toString(): String = "PagerState{minPage=$minPage, maxPage=$maxPage, " +
        "currentPage=$currentPage, currentPageOffset=$currentPageOffset}"
}

@Immutable
private data class PageData(val page: Int) : ParentDataModifier {
    override fun Density.modifyParentData(parentData: Any?): Any? = this@PageData
}

private val Measurable.page: Int
    get() = (parentData as? PageData)?.page ?: error("no PageData for measurable $this")

@Composable
fun Pager(
    state: PagerState,
    offscreenLimit: Int = 2,
    modifier: Modifier = Modifier,
    itemSpacing: Dp = 0.dp,
    pageContent: @Composable PagerScope.(PaddingValues) -> Unit
) {
    var pageSize by remember { mutableStateOf(0) }
    val halfSpacing = itemSpacing / 2
    val spacingContent = PaddingValues(halfSpacing, 0.dp, halfSpacing, 0.dp)
    Layout(
        children = {
            val minPage = (state.currentPage - offscreenLimit).coerceAtLeast(state.minPage)
            val maxPage = (state.currentPage + offscreenLimit).coerceAtMost(state.maxPage)

            for (page in minPage..maxPage) {
                val pageData = PageData(page)
                val scope = PagerScope(state, page)
                key(pageData) {
                    Box(alignment = Alignment.Center, modifier = pageData) {
                        scope.pageContent(spacingContent)
                    }
                }
            }
        },
        modifier = modifier.draggable(
            orientation = Orientation.Horizontal,
            onDragStarted = {
                state.selectionState = PagerState.SelectionState.Undecided
            },
            onDragStopped = { velocity ->
                // Velocity is in pixels per second, but we deal in percentage offsets, so we
                // need to scale the velocity to match
                state.fling(velocity / pageSize)
            }
        ) { dy ->
            with(state) {
                val pos = pageSize * currentPageOffset
                val max = if (currentPage == minPage) 0 else pageSize * offscreenLimit
                val min = if (currentPage == maxPage) 0 else -pageSize * offscreenLimit
                val newPos = (pos + dy).coerceIn(min.toFloat(), max.toFloat())
                currentPageOffset = newPos / pageSize
            }
        }
    ) { measurables, constraints ->
        // First measure non match parent size children to get the size of the Box.
        val placeablesByPage = HashMap<Int, Placeable>(measurables.size)
        var pagerHeight = constraints.minHeight
        val currentPage = state.currentPage
        val offset = state.currentPageOffset
        val minRelaxedConstraints = constraints.copy(minWidth = 0, minHeight = 0)
        measurables.fastForEachIndexed { index, measurable ->
            val placeable = measurable.measure(minRelaxedConstraints)
            placeablesByPage[measurable.page] = placeable
            pagerHeight = max(pagerHeight, placeable.height)
        }

        layout(constraints.maxWidth, pagerHeight) {
            placeablesByPage
                .forEach { (page, placeable) ->
                    // TODO: current this centers each page. We should investigate reading
                    //  gravity modifiers on the child, or maybe as a param to Pager.
                    val xCenterOffset = (constraints.maxWidth - placeable.width) / 2
                    val yCenterOffset = (pagerHeight - placeable.height) / 2

                    if (currentPage == page) {
                        pageSize = placeable.width
                    }

                    val xItemOffset = ((page + offset - currentPage) * placeable.width).roundToInt()

                    placeable.place(
                        x = 0 + xItemOffset,
                        y = yCenterOffset
                    )
                }
        }
    }
}

/**
 * Scope for [Pager] content.
 */
class PagerScope(
    private val state: PagerState,
    val page: Int
) {
    /**
     * Returns the current selected page
     */
    val currentPage: Int
        get() = state.currentPage

    /**
     * Returns the current selected page offset
     */
    val currentPageOffset: Float
        get() = state.currentPageOffset

    /**
     * Returns the current selection state
     */
    val selectionState: PagerState.SelectionState
        get() = state.selectionState
}
