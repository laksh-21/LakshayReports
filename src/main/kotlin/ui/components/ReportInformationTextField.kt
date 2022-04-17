package ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ui.theme.informationTextFieldShape

@Composable
fun ReportInformationTextField(hint: String) {
    OutlinedTextField(
        modifier = Modifier,
        value = "",
        onValueChange = {},
        placeholder = { Text(hint) },
        shape = MaterialTheme.shapes.informationTextFieldShape,
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(),
    )
}

@Preview
@Composable
fun ReportInformationTextFieldDemo() {
    ReportInformationTextField("Patient Info!")
}
