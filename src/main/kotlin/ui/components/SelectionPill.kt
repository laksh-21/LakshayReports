package ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.*
import androidx.compose.ui.input.key.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ui.theme.PillBorderSelectedColor
import ui.theme.PillBorderUnselectedColor
import utils.ui.Selectable

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun <T> SelectionPill(
    modifier: Modifier = Modifier,
    selectableList: List<Selectable<T>>,
    defaultValue: T = selectableList[0].selectableValue,
    onSelectionChanged: (T) -> Unit = {},
) {
    assert(selectableList.any { it.selectableValue == defaultValue }) {
        "Default value not in list"
    }
    val selected = rememberSaveable { mutableStateOf(selectableList.indexOfFirst { it.selectableValue == defaultValue }) }
    Row(
        modifier = modifier
            .onPreviewKeyEvent {
                if (it.type == KeyEventType.KeyUp) {
                    when (it.key) {
                        Key.DirectionRight -> {
                            if (selected.value != selectableList.size - 1) {
                                selected.value += 1
                                onSelectionChanged(selectableList[selected.value].selectableValue)
                            }
                            true
                        }
                        Key.DirectionLeft -> {
                            if (selected.value != 0) {
                                selected.value -= 1
                                onSelectionChanged(selectableList[selected.value].selectableValue)
                            }
                            true
                        }
                        else -> false
                    }
                } else false
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        selectableList.forEachIndexed { index, selectable ->
            PillColumn(
                value = selectable.selectableName,
                selected = (index == selected.value),
                position = index,
                size = selectableList.size,
                onClick = {
                    selected.value = index
                    onSelectionChanged(selectableList[selected.value].selectableValue)
                }
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun RowScope.PillColumn(
    value: String,
    selected: Boolean,
    position: Int,
    size: Int,
    onClick: () -> Unit = {},
) {
    val (topStart, topEnd, bottomStart, bottomEnd) = when (position) {
        0 -> listOf(50, 0, 50, 0)
        size - 1 -> listOf(0, 50, 0, 50)
        else -> listOf(0, 0, 0, 0)
    }
    val shape = RoundedCornerShape(
        topStart,
        topEnd,
        bottomEnd,
        bottomStart
    )
    val color = if (selected) {
        PillBorderSelectedColor
    } else {
        PillBorderUnselectedColor
    }
    Column(
        modifier = Modifier.weight(1f),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Surface(
            shape = shape,
            border = BorderStroke(1.dp, color),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = value,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onClick() }
                    .padding(horizontal = 10.dp, vertical = 6.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun SelectionPillDemo() {
    val list = listOf(
        Selectable(false, "False"),
        Selectable(false, "False"),
        Selectable(false, "False"),
    )
    SelectionPill(selectableList = list)
}
