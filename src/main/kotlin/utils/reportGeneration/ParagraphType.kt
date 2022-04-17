package utils.reportGeneration

sealed class ParagraphType {
    object BulletedParagraph : ParagraphType()
    object IntroParagraph : ParagraphType()
    object Other : ParagraphType()
}
