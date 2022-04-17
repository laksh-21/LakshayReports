package ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.LocalContentColor
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ui.theme.TextFieldBackgroundColor
import utils.ui.FilterType

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RowScope.CustomTextField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChanged: (String) -> Unit,
    placeholderText: String,
    fontSize: TextUnit = MaterialTheme.typography.body2.fontSize,
    isCentered: Boolean = true,
    filterType: FilterType = FilterType.NoFilter,
) {
    val padding = (fontSize.value.div(1.5f).toInt()).dp
    val textAlign = if (isCentered) TextAlign.Center else TextAlign.Start
    val contentAlign = if (isCentered) Alignment.Center else Alignment.TopStart
    val hasFocus = remember { mutableStateOf(false) }
    val borderStroke = if (hasFocus.value) {
        BorderStroke(1.dp, Color.Black)
    } else {
        BorderStroke(0.dp, Color.Transparent)
    }
    val focusManager = LocalFocusManager.current
    BasicTextField(
        value = text,
        modifier = modifier
            .onFocusChanged {
                hasFocus.value = it.hasFocus
            }
            .border(borderStroke, MaterialTheme.shapes.medium)
            .weight(1f)
            .background(
                TextFieldBackgroundColor,
                MaterialTheme.shapes.medium,
            )
            .padding(horizontal = 8.dp, vertical = padding),
        onValueChange = {
            onTextChanged(filterType.filterText(it))
        },
        singleLine = true,
        cursorBrush = SolidColor(MaterialTheme.colors.primary),
        textStyle = LocalTextStyle.current.copy(
            color = MaterialTheme.colors.onSurface,
            fontSize = fontSize,
            textAlign = textAlign
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    contentAlignment = contentAlign,
                    modifier = Modifier.weight(1f)
                ) {
                    if (text.isEmpty()) Text(
                        placeholderText,
                        style = LocalTextStyle.current.copy(
                            color = LocalContentColor.current.copy(alpha = 0.3f),
                            fontSize = fontSize,
                            textAlign = textAlign
                        )
                    )
                    innerTextField()
                }
            }
        },
    )
}

@Preview
@Composable
fun CustomTextFieldDemo() {
    val text = rememberSaveable { mutableStateOf("") }
    Row {
        CustomTextField(
            modifier = Modifier,
            text = text.value,
            onTextChanged = { text.value = it },
            placeholderText = "Search",
            fontSize = 12.sp,
            filterType = FilterType.FloatingPoint,
        )
    }
}
