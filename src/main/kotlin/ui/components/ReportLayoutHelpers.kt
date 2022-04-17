package ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.apache.poi.xwpf.usermodel.ParagraphAlignment
import utils.TextType

@Composable
fun RowScope.ReportColumn(content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier.weight(1f).padding(horizontal = 48.dp),
        horizontalAlignment = Alignment.Start
    ) {
        content()
    }
}

@Composable
fun ReportRow(content: @Composable RowScope.() -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        content()
    }
}

@Composable
fun ReportText(textType: TextType, text: String) {
    val alignment = when (textType.alignment) {
        ParagraphAlignment.START -> TextAlign.Start
        ParagraphAlignment.CENTER -> TextAlign.Center
        ParagraphAlignment.END -> TextAlign.End
        else -> TextAlign.Start
    }
    val modifier = if (textType is TextType.HeaderText || textType is TextType.HeaderSubtitle) {
        Modifier.fillMaxWidth()
    } else {
        Modifier
    }
    Text(
        modifier = modifier,
        text = text,
        textAlign = alignment,
        fontStyle = if (textType.isItalic) FontStyle.Italic else FontStyle.Normal,
        fontWeight = if (textType.isBold) FontWeight.Bold else FontWeight.Normal,
        fontSize = textType.fontSize
    )
}

@Composable
fun TitleText(text: String) {
    Text(
        text = text,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
    )
}
