package domain.model.report

import org.apache.poi.xwpf.usermodel.XWPFDocument
import utils.TextType
import utils.reportGeneration.ParagraphType
import utils.reportGeneration.generateParagraph

data class ReportFooter(
    val footerText: TextType.Footer =
        TextType.Footer(
            "Ultrasonologist",
            "Dr. Usha Singh",
            "M.B.B.S"
        ),
    val footerSubtitle: TextType.FooterSubtitle =
        TextType.FooterSubtitle("Level 1 sonography done. For congenital anomaly, level 2 sonography is advised.")
) {
    fun generateFormattedParagraph(document: XWPFDocument) {
        val paragraphType = ParagraphType.Other
        var paragraph = document.generateParagraph(paragraphType, footerSubtitle.alignment)
        footerSubtitle.generateFormattedRun(paragraph)
        paragraph = document.generateParagraph(paragraphType, footerText.alignment)
        footerText.generateFormattedRun(paragraph)
    }
}
