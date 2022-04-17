package ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable

@Composable
fun ReportInformationTextValue(
    title: String,
    textFieldHint: String,
    onTextFieldValueChange: (String) -> Unit = {},
) {
    ReportInformationRow {
        ReportInformationText(title)
        ReportInformationTextField(textFieldHint)
    }
}

@Preview
@Composable
fun ReportInformationTextValueDemo() {
    ReportInformationTextValue(
        title = "Name",
        textFieldHint = "Patient Name",
    )
}
