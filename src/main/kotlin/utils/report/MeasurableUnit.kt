package utils.report

import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import utils.ui.FilterType

sealed class MeasurableUnit(
    open val filterType: FilterType = FilterType.Integer,
    val fontSize: TextUnit = 12.sp,
) {
    sealed class SingleValueUnit(
        open val unit: String,
        override val filterType: FilterType
    ) : MeasurableUnit(
        filterType = filterType
    ) {
        sealed class SingleValueIntegerUnit(
            open val value: Int,
            override val unit: String
        ) : SingleValueUnit(unit, FilterType.Integer) {
            open fun getFormattedValue(): String {
                return "$value $unit"
            }
        }

        sealed class SingleValueTextUnit(
            open val value: String,
            override val unit: String
        ) : SingleValueUnit(unit, FilterType.NoFilter) {
            open fun getFormattedValue(): String {
                return "$value $unit"
            }
        }

        sealed class SingleValueFloatUnit(
            open val value: Float,
            override val unit: String
        ) : SingleValueUnit(unit, FilterType.FloatingPoint) {
            open fun getFormattedValue(): String {
                return "${String.format("%.2f", value)} $unit"
            }
        }
    }

    sealed class DoubleValueDoubleMetricUnit(
        open val firstValue: Int,
        open val firstUnit: String,
        open val secondValue: Int,
        open val secondUnit: String
    ) : MeasurableUnit() {
        open fun getFormattedValue(): String {
            return "$firstValue $firstUnit $secondValue $secondUnit"
        }
    }

    sealed class RangeValueUnit(
        open val firstValue: Int,
        open val secondValue: Int,
        val unit: String
    ) : MeasurableUnit() {
        open fun getFormattedValue(): String {
            return "$firstValue ± $secondValue $unit"
        }
    }
}
