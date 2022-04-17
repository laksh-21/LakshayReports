package utils.ui

import java.util.*

sealed class FilterType(val maxLength: Int) {

    object NoFilter : FilterType(100)

    object Text : FilterType(20) {
        override fun filterText(text: String): String {
            return capitalizeEachWord(text).filter { (it.isLetter() || it == ' ') }.take(maxLength).trim()
        }
    }

    object Integer : FilterType(8) {
        override fun filterText(text: String): String {
            return text.filter { it.isDigit() }.take(maxLength).trim()
        }
    }

    object FloatingPoint : FilterType(8) {
        override fun filterText(text: String): String {
            return if (text.contains(".")) {
                text.split(".").run {
                    var res = "${this[0]}.${this[1]}"
                    this.forEachIndexed { index, s ->
                        if (index > 1) res += s
                    }
                    res.take(maxLength)
                }
            } else {
                text.filter { it.isDigit() }.take(maxLength).trim()
            }
        }
    }

    open fun filterText(text: String): String {
        return text
    }

    fun capitalizeEachWord(text: String): String {
        val words = text.split(" ")
        var result = ""
        for (word in words) {
            result += word.lowercase().replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() } + " "
        }
        return result
    }
}
