package utils.report

import utils.Utils
import java.util.*

data class FetalWeight(
    var weight: Int,
    var range: Int,
) : MeasurableUnit.RangeValueUnit(
    weight,
    range,
    "gms"
)

data class EstimatedDeliveryDate(
    val date: String
) : MeasurableUnit.SingleValueUnit.SingleValueTextUnit(date, "(40 weeks)") {
    override fun getFormattedValue(): String {
        return "estimated date of delivery (EDD): $value $unit"
    }
}

object HeartRate : MeasurableUnit.SingleValueUnit.SingleValueIntegerUnit(
    (140..150).random(), "BPM"
)

data class Measurements(
    override val value: Float
) : MeasurableUnit.SingleValueUnit.SingleValueFloatUnit(
    value, "cm"
)

data class FetalAge(
    val weeks: Int,
    val days: Int,
) : MeasurableUnit.DoubleValueDoubleMetricUnit(
    firstValue = weeks,
    firstUnit = "weeks",
    secondValue = days,
    secondUnit = "days"
) {
    override fun getFormattedValue(): String {
        return super.getFormattedValue() + " ± 7 $secondUnit gestational age"
    }
}

data class Age(
    val age: Int
) : MeasurableUnit.SingleValueUnit.SingleValueIntegerUnit(age, "years")
