package utils.reportGeneration

import org.apache.poi.xwpf.usermodel.XWPFAbstractNum
import org.apache.poi.xwpf.usermodel.XWPFParagraph
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum
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
    numID = generateBulletedListStyle()
    ctp.pPr.numPr.addNewIlvl().setVal(BigInteger.valueOf(0))
}

private fun XWPFParagraph.generateBulletedListStyle(): BigInteger? {
    return try {
        val numbering = document.createNumbering()
        // generate numbering style from XML
        val abstractNum = CTAbstractNum.Factory.parse(BULLET_LIST_TEMPLATE)
        val abs = XWPFAbstractNum(abstractNum, numbering)

        // find available id in document
        var id = BigInteger.valueOf(0)
//        var found = false
//        while (!found) {
//            val o: XWPFAbstractNum? = numbering.getAbstractNum(id)
//            found = o == null
//            if (!found) id = id.add(BigInteger.ZERO)
//        }
        // assign id
        abs.abstractNum.abstractNumId = id
        // add to numbering, should get back same id
        id = numbering.addAbstractNum(abs)
        // add to num list, result is numid
        document.numbering.addNum(id)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}
