package ui.report

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.components.CustomTextField
import ui.theme.UnitSubtitleText
import utils.report.MeasurableUnit

@Composable
fun MeasurableUnitFields(
    firstModifier: Modifier = Modifier,
    secondModifier: Modifier = Modifier,
    measurableUnit: MeasurableUnit,
    firstText: String = "",
    secondText: String = "",
    onFirstTextChanged: (String) -> Unit = {},
    onSecondTextChanged: (String) -> Unit = {},
    firstPlaceholderText: String = "",
    secondPlaceholderText: String = "",
) {
    Row(
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier.padding(horizontal = 4.dp)
    ) {
        when (measurableUnit) {
            is MeasurableUnit.SingleValueUnit -> {
                SingleValueField(
                    modifier = firstModifier,
                    unit = measurableUnit,
                    text = firstText,
                    placeholderText = firstPlaceholderText,
                    onTextChanged = {
                        onFirstTextChanged(it)
                    },
                )
            }
            is MeasurableUnit.DoubleValueDoubleMetricUnit -> {
                TwoValueField(
                    firstModifier = firstModifier,
                    secondModifier = secondModifier,
                    unit = measurableUnit,
                    firstText = firstText,
                    secondText = secondText,
                    firstPlaceholderText = firstPlaceholderText,
                    secondPlaceholderText = secondPlaceholderText,
                    onFirstTextChanged = onFirstTextChanged,
                    onSecondTextChanged = onSecondTextChanged,
                )
            }
            is MeasurableUnit.RangeValueUnit -> {
                RangeValueField(
                    firstModifier = firstModifier,
                    secondModifier = secondModifier,
                    unit = measurableUnit,
                    firstText = firstText,
                    secondText = secondText,
                    firstPlaceholderText = firstPlaceholderText,
                    secondPlaceholderText = secondPlaceholderText,
                    onFirstTextChanged = onFirstTextChanged,
                    onSecondTextChanged = onSecondTextChanged,
                )
            }
        }
    }
}

@Composable
fun RowScope.SingleValueField(
    modifier: Modifier,
    unit: MeasurableUnit.SingleValueUnit,
    text: String,
    placeholderText: String,
    onTextChanged: (String) -> Unit,
) {
    CustomTextField(
        modifier = modifier,
        text = text,
        onTextChanged = onTextChanged,
        placeholderText = placeholderText,
//        fontSize = unit.fontSize,
        isCentered = true,
        filterType = unit.filterType,
    )
    MeasurableUnitSpacer()
    Text(
        text = unit.unit,
        style = MaterialTheme.typography.UnitSubtitleText,
//        fontSize = unit.fontSize
    )
}

@Composable
fun RowScope.TwoValueField(
    firstModifier: Modifier,
    secondModifier: Modifier,
    unit: MeasurableUnit.DoubleValueDoubleMetricUnit,
    firstText: String,
    secondText: String,
    firstPlaceholderText: String,
    secondPlaceholderText: String,
    onFirstTextChanged: (String) -> Unit,
    onSecondTextChanged: (String) -> Unit,
) {
    CustomTextField(
        modifier = firstModifier,
        text = firstText,
        onTextChanged = onFirstTextChanged,
        placeholderText = firstPlaceholderText,
//        fontSize = unit.fontSize,
        isCentered = true,
        filterType = unit.filterType,
    )
    MeasurableUnitSpacer()
    Text(
        text = unit.firstUnit,
        style = MaterialTheme.typography.UnitSubtitleText,
//        fontSize = unit.fontSize
    )
    MeasurableUnitSpacer()
    CustomTextField(
        modifier = secondModifier,
        text = secondText,
        onTextChanged = onSecondTextChanged,
        placeholderText = secondPlaceholderText,
//        fontSize = unit.fontSize,
        isCentered = true,
        filterType = unit.filterType,
    )
    MeasurableUnitSpacer()
    Text(
        text = unit.secondUnit,
        style = MaterialTheme.typography.UnitSubtitleText,
//        fontSize = unit.fontSize
    )
}

@Composable
fun RowScope.RangeValueField(
    firstModifier: Modifier,
    secondModifier: Modifier,
    unit: MeasurableUnit.RangeValueUnit,
    firstText: String,
    secondText: String,
    firstPlaceholderText: String,
    secondPlaceholderText: String,
    onFirstTextChanged: (String) -> Unit,
    onSecondTextChanged: (String) -> Unit,
) {
    CustomTextField(
        modifier = firstModifier,
        text = firstText,
        onTextChanged = onFirstTextChanged,
        placeholderText = firstPlaceholderText,
//        fontSize = unit.fontSize,
        isCentered = true,
        filterType = unit.filterType,
    )
    MeasurableUnitSpacer()
    Text(
        text = "±",
        style = MaterialTheme.typography.UnitSubtitleText,
//        fontSize = unit.fontSize
    )
    MeasurableUnitSpacer()
    CustomTextField(
        modifier = secondModifier,
        text = secondText,
        onTextChanged = onSecondTextChanged,
        placeholderText = secondPlaceholderText,
//        fontSize = unit.fontSize,
        isCentered = true,
        filterType = unit.filterType,
    )
    MeasurableUnitSpacer()
    Text(
        text = unit.unit,
        style = MaterialTheme.typography.UnitSubtitleText,
//        fontSize = unit.fontSize
    )
}

@Composable
fun MeasurableUnitSpacer() {
    Spacer(modifier = Modifier.width(8.dp))
}
