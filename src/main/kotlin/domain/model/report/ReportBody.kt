package domain.model.report

import org.apache.poi.xwpf.usermodel.XWPFDocument
import utils.TextType
import utils.reportGeneration.ParagraphType
import utils.reportGeneration.generateParagraph

data class ReportBody(
    val title: TextType.SectionHeader = TextType.SectionHeader("Report"),
    val report: List<TextType.ReportContent>
) {
    fun generateFormattedParagraph(document: XWPFDocument) {
        val paragraphType = ParagraphType.BulletedParagraph
        var paragraph = document.generateParagraph(ParagraphType.Other, title.alignment)
        title.generateFormattedRun(paragraph)
        report.forEach {
            paragraph = document.generateParagraph(paragraphType, it.alignment)
            it.generateFormattedRun(paragraph)
        }
    }
}
