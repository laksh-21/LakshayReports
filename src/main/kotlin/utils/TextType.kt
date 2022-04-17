package utils

import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import org.apache.poi.xwpf.usermodel.ParagraphAlignment
import org.apache.poi.xwpf.usermodel.UnderlinePatterns
import org.apache.poi.xwpf.usermodel.XWPFParagraph
import org.apache.poi.xwpf.usermodel.XWPFRun

sealed class TextType(
    val isBold: Boolean = false,
    val isItalic: Boolean = false,
    val isFullyUnderLine: Boolean = false,
    val fontSize: TextUnit = 12.sp,
    val isBulleted: Boolean = false,
    val isCapitalized: Boolean = false,
    val alignment: ParagraphAlignment = ParagraphAlignment.LEFT
) {
    data class HeaderText(val text: String) : TextType(
        isBold = true,
        fontSize = 38.sp,
        alignment = ParagraphAlignment.CENTER
    ) {
        fun generateFormattedRun(paragraph: XWPFParagraph) {
            generateRun(paragraph).apply {
                setText(text)
            }
        }
    }

    data class HeaderSubtitle(val text: String) : TextType(
        alignment = ParagraphAlignment.CENTER,
        fontSize = 14.sp
    ) {
        fun generateFormattedRun(paragraph: XWPFParagraph) {
            generateRun(paragraph).apply {
                setText(text)
                addBreak()
            }
        }
    }

    data class Intro(
        val title: String,
        val value: String,
    ) : TextType(
        isBold = true,
    ) {
        fun generateFormattedRun(paragraph: XWPFParagraph) {
            generateRun(paragraph).apply {
                underline = UnderlinePatterns.SINGLE
                setText(title)
            }
            generateRun(paragraph).apply {
                setText(": $value")
            }
        }
    }

    data class SectionHeader(
        val title: String,
        val breakAtEnd: Boolean = true
    ) : TextType(
        isBold = true,
        isFullyUnderLine = true,
        fontSize = 14.sp,
    ) {
        fun generateFormattedRun(paragraph: XWPFParagraph) {
            generateRun(paragraph).apply {
                addBreak()
                setText(title)
                if (breakAtEnd) addBreak()
            }
        }
    }

    data class ReportContent(
        val content: String,
    ) : TextType(
        isBulleted = true,
    ) {
        fun generateFormattedRun(paragraph: XWPFParagraph) {
            generateRun(paragraph).apply {
                setText(content)
            }
        }
    }

    data class OpinionContent(
        val text: String
    ) : TextType(fontSize = 14.sp) {
        fun generateFormattedRun(paragraph: XWPFParagraph) {
            generateRun(paragraph).apply {
                setText(": $text")
            }
        }
    }

    data class FooterSubtitle(val text: String) : TextType(
        fontSize = 8.sp,
        isBold = true,
        alignment = ParagraphAlignment.CENTER
    ) {
        fun generateFormattedRun(paragraph: XWPFParagraph) {
            generateRun(paragraph).apply {
                addBreak()
                setText(text.uppercase())
            }
        }
    }

    data class Footer(
        val designation: String,
        val name: String,
        val degree: String,
    ) : TextType(
        alignment = ParagraphAlignment.RIGHT
    ) {
        fun generateFormattedRun(paragraph: XWPFParagraph) {
            generateRun(paragraph).apply {
                repeat(3) { addBreak() }
                setText(designation)
                addBreak()
                setText(name)
                addBreak()
                setText(degree)
            }
        }
    }

    protected fun generateRun(paragraph: XWPFParagraph): XWPFRun {
        return paragraph.createRun().apply {
            this.isBold = this@TextType.isBold
            this.isItalic = this@TextType.isItalic
            this.fontSize = this@TextType.fontSize.value.toInt()
            this.isCapitalized = this@TextType.isCapitalized
            if (this@TextType.isFullyUnderLine) {
                this.underline = UnderlinePatterns.SINGLE
            }
        }
    }
}
