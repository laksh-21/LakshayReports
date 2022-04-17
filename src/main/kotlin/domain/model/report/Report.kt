package domain.model.report

import org.apache.poi.xwpf.usermodel.XWPFDocument

data class Report(
    val header: ReportHeader,
    val intro: ReportIntro,
    val reportBody: ReportBody,
    val reportOpinion: ReportOpinion,
    val footer: ReportFooter
) {
    fun generateFormattedDocument(): XWPFDocument {
        val document = XWPFDocument()
        document.apply {
            header.generateFormattedParagraph(this)
            intro.generateFormattedParagraph(this)
            reportBody.generateFormattedParagraph(this)
            reportOpinion.generateFormattedParagraph(this)
            footer.generateFormattedParagraph(this)
        }
        return document
    }
}
