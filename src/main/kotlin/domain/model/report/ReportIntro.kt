package domain.model.report

import org.apache.poi.xwpf.usermodel.XWPFDocument
import utils.TextType
import utils.Utils
import utils.report.Age
import utils.report.Gender
import utils.reportGeneration.ParagraphType
import utils.reportGeneration.generateParagraph

data class ReportIntro(
    val title: TextType.SectionHeader = TextType.SectionHeader("Patient Information"),
    val list: List<TextType.Intro>
) {

    fun generateFormattedParagraph(document: XWPFDocument) {
        val paragraphType = ParagraphType.IntroParagraph
        var paragraph = document.generateParagraph(ParagraphType.Other, title.alignment)
        title.generateFormattedRun(paragraph)
        list.forEach {
            paragraph = document.generateParagraph(paragraphType, it.alignment)
            it.generateFormattedRun(paragraph)
        }
    }

    companion object {
        fun generateFromValues(
            name: String,
            age: Age,
            sex: Gender,
            referredBy: String
        ): ReportIntro {
            return ReportIntro(
                list = listOf(
                    TextType.Intro("Patient Name", name),
                    TextType.Intro("Patient Age", age.getFormattedValue()),
                    TextType.Intro("Patient Gender", sex.name),
                    TextType.Intro("Date", Utils.getTodayDate()),
                    TextType.Intro("Referred By", referredBy)
                ),
            )
        }
    }
}
