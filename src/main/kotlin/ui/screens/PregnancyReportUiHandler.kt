package ui.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import domain.model.pregnancyReport.PregnancyReport
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import utils.Utils
import utils.report.*
import utils.reportGeneration.WordGenerator

class PregnancyReportUiHandler {
    val name = mutableStateOf("")
    val age = mutableStateOf("20")

    val isCRL = mutableStateOf(false)
    val CRL = mutableStateOf("0")
    val BPD = mutableStateOf("0")
    val FL = mutableStateOf("0")
    val presentation: MutableState<Presentation> = mutableStateOf(Presentation.Cephalic)
    val placentaArea: MutableState<Area> = mutableStateOf(Area.Anterior)
    val placentaPositioning: MutableState<Positioning> = mutableStateOf(Positioning.Upper)
    val amnioticFluidAdequate = mutableStateOf(true)
    val fetalMovementNormal = mutableStateOf(true)
    val cardiacActivityPresent = mutableStateOf(true)
    val fetalAgeWeeks = mutableStateOf("20")
    val fetalAgeDays = mutableStateOf("2")
    val fetalWeight = mutableStateOf("400")
    val fetalWeightRange = mutableStateOf("100")
    val estimatedDeliveryDate = mutableStateOf(getEDD())

    fun calculateEDD() {
        estimatedDeliveryDate.value = getEDD()
    }

    private fun getEDD(): String {
        return if (fetalAgeWeeks.value.isNotEmpty() && fetalAgeDays.value.isNotEmpty()) {
            val daysRemaining = 40 * 7 - (fetalAgeWeeks.value.toInt() * 7 + fetalAgeDays.value.toInt())
            Utils.getDateAhead(0, daysRemaining)
        } else estimatedDeliveryDate.value
    }

    fun generateReport() {
        val report = PregnancyReport(
            name = name.value,
            age = Age(age.value.toInt()),
            gender = Gender.Female,
            referredBy = "",
            isCRL = isCRL.value,
            presentation = presentation.value,
            cardiacActivity = cardiacActivityPresent.value,
            fetalHeartRate = HeartRate,
            amnioticFluidAdequate = amnioticFluidAdequate.value,
            placentaPlacement = Placenta(placentaArea.value, placentaPositioning.value),
            fetalMovementNormal = fetalMovementNormal.value,
            fetalAge = FetalAge(fetalAgeWeeks.value.toInt(), fetalAgeDays.value.toInt()),
            fetalWeight = FetalWeight(fetalWeight.value.toInt(), fetalWeightRange.value.toInt()),
            BPD = Measurements(BPD.value.toFloat()),
            FL = Measurements(FL.value.toFloat()),
            CRL = Measurements(CRL.value.toFloat()),
            estimatedDeliveryDate = EstimatedDeliveryDate(estimatedDeliveryDate.value),
        )
        val reportDoc = report.generateReportObject()
        CoroutineScope(Dispatchers.IO).launch {
            WordGenerator.generateReport(reportDoc.generateFormattedDocument(), name.value)
        }
    }
}
