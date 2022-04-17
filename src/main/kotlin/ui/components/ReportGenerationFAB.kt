package ui.components

import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun ReportGenerationFAB(onClick: () -> Unit) {
    ExtendedFloatingActionButton(
        text = { Text("Generate Report") },
        onClick = onClick,
        shape = RoundedCornerShape(CornerSize(20)),
        backgroundColor = Color(0xFF1e1e1e),
        contentColor = Color.White,
    )
}