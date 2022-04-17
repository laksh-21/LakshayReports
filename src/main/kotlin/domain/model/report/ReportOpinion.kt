package domain.model.report

import org.apache.poi.xwpf.usermodel.XWPFDocument
import utils.TextType
import utils.reportGeneration.ParagraphType
import utils.reportGeneration.generateParagraph

data class ReportOpinion(
    val title: TextType.SectionHeader = TextType.SectionHeader("Opinion", false),
    val content: TextType.OpinionContent,
) {
    fun generateFormattedParagraph(document: XWPFDocument) {
        val paragraph = document.generateParagraph(ParagraphType.Other)
        title.generateFormattedRun(paragraph)
        content.generateFormattedRun(paragraph)
    }
}
