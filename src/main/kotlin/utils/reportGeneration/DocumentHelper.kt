package utils.reportGeneration

import org.apache.poi.xwpf.usermodel.ParagraphAlignment
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.apache.poi.xwpf.usermodel.XWPFParagraph

fun XWPFDocument.generateParagraph(
    paragraphType: ParagraphType,
    paragraphAlignment: ParagraphAlignment = ParagraphAlignment.LEFT
): XWPFParagraph {
    val paragraph = when (paragraphType) {
        is ParagraphType.BulletedParagraph -> generateBulletedParagraph(this)
        is ParagraphType.IntroParagraph -> generateIntroParagraph(this)
        else -> createParagraph()
    }.apply {
        alignment = paragraphAlignment
    }
    return paragraph
}

private fun generateBulletedParagraph(document: XWPFDocument): XWPFParagraph {
    val paragraph = document.createParagraph().apply {
        setSingleLineSpacing()
        setBulletedListStyle()
    }
    return paragraph
}

private fun generateIntroParagraph(document: XWPFDocument): XWPFParagraph {
    val paragraph = document.createParagraph().apply {
        setSingleLineSpacing(100)
    }
    return paragraph
}
