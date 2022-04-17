package domain.model.report

import org.apache.poi.xwpf.usermodel.ParagraphAlignment
import org.apache.poi.xwpf.usermodel.XWPFDocument
import utils.TextType
import utils.reportGeneration.ParagraphType
import utils.reportGeneration.generateParagraph

data class ReportHeader(
    val headerText: TextType.HeaderText = TextType.HeaderText("Lakshay Hospital"),
    val headerSubtitle: TextType.HeaderSubtitle =
        TextType.HeaderSubtitle("Near St. Mary's School, Circular Road, Bijnor"),
) {
    fun generateFormattedParagraph(document: XWPFDocument) {
        val type = ParagraphType.Other
        var paragraph = document.generateParagraph(type, headerText.alignment)
        headerText.generateFormattedRun(paragraph)
        paragraph = document.generateParagraph(type, headerSubtitle.alignment)
        headerSubtitle.generateFormattedRun(paragraph)
    }
}
