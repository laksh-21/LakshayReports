package ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ui.Constants

@Composable
fun ReportInformationText(text: String) {
    Text(
        modifier = Modifier,
        text = "$text: ",
        fontSize = Constants.titleTextSize
    )
}

@Preview
@Composable
fun ReportInformationDemo() {
    ReportInformationText("Patient Name")
}
