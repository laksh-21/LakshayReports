package ui.report

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import domain.model.report.ReportHeader
import ui.components.ReportText

@Composable
fun ReportHeader() {
    val header = ReportHeader()
    ReportText(header.headerText, header.headerText.text)
    ReportText(header.headerSubtitle, header.headerSubtitle.text)
    Spacer(Modifier.height(10.dp))
}
