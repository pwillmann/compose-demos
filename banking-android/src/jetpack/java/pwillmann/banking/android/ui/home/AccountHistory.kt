package pwillmann.banking.android.ui.home

import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.ui.tooling.preview.Devices
import androidx.ui.tooling.preview.Preview
import banking.common.data.model.Account
import banking.common.data.model.AccountHistoryItem
import banking.common.data.repository.AccountRepository
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.MarkerImage
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.pwillmann.composedemos.banking.util.printNoDecimals
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale
import kotlinx.datetime.toJavaLocalDate
import pwillmann.banking.android.R
import pwillmann.banking.android.theme.BankingTheme
import pwillmann.banking.android.theme.ThemeOpacity
import pwillmann.banking.android.theme.greyLight
import pwillmann.banking.android.theme.purpleLight
import timber.log.Timber

@Preview(showBackground = true)
@Preview(device = Devices.PIXEL_2)
@Composable
fun PreviewAccountHistoryGraph() {
    BankingTheme {
        AccountHistory(AccountRepository.instance.getNewAccount())
    }
}

@Composable
fun AccountHistory(account: Account) {
    Timber.d("render AccountHistory for $account")
    Surface(
        color = MaterialTheme.colors.background,
        contentColor = contentColorFor(color = MaterialTheme.colors.background),
        modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(vertical = 16.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                "Balance History",
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            AccountHistoryGraph(
                historyData = account.accountHistory,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
internal fun AccountHistoryGraph(
    historyData: List<AccountHistoryItem>,
    modifier: Modifier = Modifier
) {
    val selectedValue: MutableState<Double?> = remember { mutableStateOf(null) }

    val lineData = remember(historyData) { buildBalanceChartData(historyData) }
    val context = ContextAmbient.current

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            viewBlock = {
                LineChartCustomView(
                    context = context,
                    selectedListener = object : OnChartValueSelectedListener {
                        override fun onValueSelected(e: Entry?, h: Highlight?) {
                            selectedValue.value = (e?.y?.toDouble())
                        }

                        override fun onNothingSelected() {
                            selectedValue.value = null
                        }
                    }
                )
            }
        ) { view ->
            with(view) {
                val highlight = highlighted?.firstOrNull()
                val (yMin, yMax) = getYAxisMinAndMax(historyData)
                axisLeft.axisMinimum = yMin
                axisLeft.axisMaximum = yMax
                data = lineData
                if (highlight != null) {
                    highlightValue(highlight.x, highlight.dataSetIndex)
                }
            }
        }
        val value = selectedValue.value
        if (value != null) {
            SelectedValueLabel(
                value.printNoDecimals(),
                modifier = Modifier.align(Alignment.TopCenter).padding(top = 4.dp)
            )
        }
    }
}

private fun LineChartCustomView(
    context: Context,
    selectedListener: OnChartValueSelectedListener? = null
): LineChart {
    Timber.d("Create Custom view")
    return LineChart(context).apply {
        setDrawGridBackground(false)
        setDrawBorders(false)
        legend.isEnabled = false
        axisLeft.isEnabled = false
        axisRight.isEnabled = false
        xAxis.position = XAxis.XAxisPosition.BOTTOM_INSIDE
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(false)
        xAxis.valueFormatter = BalanceChartXAxisFormatter()
        description.isEnabled = false
        xAxis.textColor = greyLight.toArgb()
        xAxis.textSize = 12f
        setPinchZoom(false)
        setScaleEnabled(false)
        isDoubleTapToZoomEnabled = false
        animateX(1000, Easing.Linear)
        marker = MarkerImage(context, R.drawable.ic_chart_indicator).apply {
            setOffset(-convertDpToPixel(4f, context), -convertDpToPixel(4f, context))
        }

        setOnChartValueSelectedListener(selectedListener)
    }
}

@Composable
internal fun SelectedValueLabel(text: String = "$ 1233", modifier: Modifier = Modifier) {
    Surface(
        color = MaterialTheme.colors.secondary,
        contentColor = contentColorFor(color = MaterialTheme.colors.secondary),
        shape = MaterialTheme.shapes.small.copy(CornerSize(10.dp)),
        modifier = modifier.wrapContentSize()
    ) {
        Text(
            text,
            style = MaterialTheme.typography.caption,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
        )
    }
}

fun convertDpToPixel(dp: Float, context: Context?): Float {
    return if (context != null) {
        val resources = context.resources
        val metrics = resources.displayMetrics
        dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    } else {
        val metrics = Resources.getSystem().displayMetrics
        dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }
}

private fun getYAxisMinAndMax(balanceHistory: List<AccountHistoryItem>): Pair<Float, Float> {
    val minBalance = balanceHistory.minOf { it.balance }
    val maxBalance = balanceHistory.maxOf { it.balance }

    val min = (minBalance - (maxBalance - minBalance)).toFloat()
    val max = (maxBalance + (maxBalance - minBalance) / 2).toFloat()

    return min to max
}

private fun buildBalanceChartData(balanceHistory: List<AccountHistoryItem>): LineData {
    val balanceEntries = balanceHistory.mapIndexed { index, item ->
        Entry(item.date.toJavaLocalDate().toEpochDay().toFloat(), item.balance.toFloat())
    }
    val lineDataSet = LineDataSet(
        balanceEntries,
        "Balance"
    ).apply {
        color = purpleLight.toArgb()
        lineWidth = 2f
        setDrawCircles(false)
        setDrawValues(false)
        isHighlightEnabled = true
        setDrawVerticalHighlightIndicator(true)
        setDrawHorizontalHighlightIndicator(false)
        highlightLineWidth = 2f
        highLightColor = greyLight.copy(alpha = ThemeOpacity.medium).toArgb()
        enableDashedHighlightLine(20f, 20f, 0f)
    }
    return LineData(lineDataSet)
}

class BalanceChartXAxisFormatter : ValueFormatter() {
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        val localDate = LocalDate.ofEpochDay(value.toLong())
        return localDate.month
            .getDisplayName(TextStyle.SHORT, Locale.getDefault())
    }
}
