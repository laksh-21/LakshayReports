package domain.model.pregnancyReport

import domain.model.report.*
import utils.TextType
import utils.Utils
import utils.report.*
import java.util.*

data class PregnancyReport(
    var name: String = "",
    var age: Age = Age(20),
    var gender: Gender = Gender.Female,
    var referredBy: String = "",
    var isCRL: Boolean = false,
    var presentation: Presentation = Presentation.Cephalic,
    var cardiacActivity: Boolean = true,
    var fetalHeartRate: HeartRate = HeartRate,
    var amnioticFluidAdequate: Boolean = true,
    var placentaPlacement: Placenta = Placenta(Area.Anterior, Positioning.Upper),
    var fetalMovementNormal: Boolean = true,
    var fetalAge: FetalAge = FetalAge(20, 0),
    var fetalWeight: FetalWeight = FetalWeight(750, 50),
    var BPD: Measurements = Measurements(20.0f),
    var FL: Measurements = Measurements(10.0f),
    var CRL: Measurements = Measurements(10.0f),
    var estimatedDeliveryDate: EstimatedDeliveryDate = EstimatedDeliveryDate(Utils.formatDate(Date()))
) {
    fun generateReportObject(): Report {
        return Report(
            header = ReportHeader(),
            footer = ReportFooter(),
            intro = ReportIntro.generateFromValues(
                name = name,
                age = age,
                sex = gender,
                referredBy = referredBy
            ),
            reportBody = generateReportBody(),
            reportOpinion = generateReportOpinion(),
        )
    }

    private fun generateReportBody(): ReportBody {
        val aliveStatus: String = if (cardiacActivity) "live" else "dead"
        val cardiacActivityState = if (cardiacActivity) "present" else "absent"
        val amnioticFluidState = if (amnioticFluidAdequate) "adequate" else "inadequate"
        val fetalMovementState = if (fetalMovementNormal) "normal" else "not normal"
        val list = mutableListOf<TextType.ReportContent>()
        list.apply {
            addContent("Single $aliveStatus fetus is seen in uterus")
            if (isCRL) {
                addContent(Presentation.Unstable.getFormattedPresentation())
            } else {
                addContent(presentation.getFormattedPresentation())
            }
            if (isCRL) {
                val value = Pair("Crown Rump Length (CRL)", CRL.getFormattedValue())
                    .singleValueDescriptionFormat()
                addContent(value)
            } else {
                var value = Pair("Biparietal Diameter (BPD)", BPD.getFormattedValue())
                    .singleValueDescriptionFormat()
                addContent(value)
                value = Pair("Femur Length (FL)", FL.getFormattedValue())
                    .singleValueDescriptionFormat()
                addContent(value)
            }
            addContent(Pair("Cardiac Activity", cardiacActivityState).singleValueDescriptionFormat())
            if (cardiacActivity) {
                val value = Pair("Fetal Heart Rate (FHR)", fetalHeartRate.getFormattedValue())
                    .singleValueDescriptionFormat()
                addContent(value)
            }
            addContent(Pair("Amniotic Fluid", amnioticFluidState).singleValueDescriptionFormat())
            addContent(placentaPlacement.getFormattedString())
            addContent(Pair("Fetal Movement", fetalMovementState).singleValueDescriptionFormat())
            if (!isCRL) {
                addContent(Pair("Fetal Weight (F.wt)", fetalWeight.getFormattedValue()).singleValueDescriptionFormat())
            }
        }
        return ReportBody(report = list)
    }

    private fun getAliveStatus(): String {
        return if (cardiacActivity) "live" else "dead"
    }

    private fun generateReportOpinion(): ReportOpinion {
        var reportContent = "Single ${getAliveStatus()} fetus with ${fetalAge.getFormattedValue()}"
        if (cardiacActivity) {
            reportContent += " and an ${estimatedDeliveryDate.getFormattedValue()}"
        }
        return ReportOpinion(content = TextType.OpinionContent(reportContent))
    }
}
