import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import ui.screens.PregnancyReportScreen
import utils.reportGeneration.WordGenerator

@Composable
@Preview
fun App() {
    MaterialTheme {
        PregnancyReportScreen()
    }
}

fun main() = application {
    val state = rememberWindowState(placement = WindowPlacement.Maximized)
    Window(
        onCloseRequest = ::exitApplication,
        state = state,
        title = "Lakshay Reports",
        icon = painterResource("lakshay_reports.png"),
    ) {
        App()
    }
    LaunchedEffect(Unit) {
        WordGenerator.clearPreviousFolders()
    }
}
