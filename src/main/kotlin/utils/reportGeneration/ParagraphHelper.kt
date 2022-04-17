package utils.reportGeneration

import org.apache.poi.xwpf.usermodel.XWPFParagraph
import org.openxmlformats.schemas.wordprocessingml.x2006.main.NumberingDocument
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLineSpacingRule
import utils.ResourcesHelper
import java.math.BigInteger

private val BULLET_LIST_TEMPLATE = ResourcesHelper.getXMLResourceAsString("bulleted_template.xml")

fun XWPFParagraph.setSingleLineSpacing(spacingValue: Long = 0L) {
    var ppr = ctp.pPr
    if (ppr == null) ppr = ctp.addNewPPr()
    val spacing = if (ppr!!.isSetSpacing) ppr.spacing else ppr.addNewSpacing()
    spacing.apply {
        after = BigInteger.valueOf(spacingValue)
        before = BigInteger.valueOf(0)
        lineRule = STLineSpacingRule.AUTO
        line = BigInteger.valueOf(240)
    }
}

fun XWPFParagraph.setBulletedListStyle() {
    val numbering = NumberingDocument.Factory.parse(BULLET_LIST_TEMPLATE)
    document.createNumbering().setNumbering(numbering.numbering)
    numID = BigInteger.ONE
    ctp.pPr.numPr.addNewIlvl().setVal(BigInteger.valueOf(0))
}
