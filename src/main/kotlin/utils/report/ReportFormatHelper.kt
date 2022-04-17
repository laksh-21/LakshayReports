package utils.report

import utils.TextType

fun Pair<String, String>.singleValueDescriptionFormat(): String {
    return "$first: $second"
}

fun MutableList<TextType.ReportContent>.addContent(value: String) {
    this.add(TextType.ReportContent(value))
}