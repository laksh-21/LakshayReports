package utils

import androidx.compose.ui.res.useResource
import java.io.BufferedReader
import java.io.InputStreamReader

object ResourcesHelper {
    fun getXMLResourceAsString(resourceName: String): String = useResource(resourceName) {
        val reader = BufferedReader(InputStreamReader(it))
        val builder = StringBuilder()
        var line: String? = reader.readLine()
        while (line != null) {
            builder.append(line)
            line = reader.readLine()
        }
        builder.replace(Regex(" +"), " ")
        return@useResource builder.toString()
    }
}
