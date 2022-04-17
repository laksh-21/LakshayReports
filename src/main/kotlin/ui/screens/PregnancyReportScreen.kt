package ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import domain.model.pregnancyReport.PregnancyReport
import ui.components.*
import ui.report.MeasurableUnitFields
import ui.report.ReportHeader
import utils.report.Area
import utils.report.Positioning
import utils.report.Presentation
import utils.ui.FilterType
import utils.ui.Selectable

@Composable
fun PregnancyReportScreen() {
    val pregnancyReport = remember { PregnancyReport() }
    val uiHandler = remember { PregnancyReportUiHandler() }
    Scaffold(
        floatingActionButton = { ReportGenerationFAB { uiHandler.generateReport() } },
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(it), contentAlignment = Alignment.Center) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(.7f)
                    .border(1.dp, Color(0xFFCECECE))
            ) {
                ReportHeader()
                val reportFirst = remember { FocusRequester() }
                PersonalInformation(uiHandler, pregnancyReport)
                ReportInformation(uiHandler, pregnancyReport)
            }
        }
    }
}

@Composable
fun ReportInformation(uiHandler: PregnancyReportUiHandler, report: PregnancyReport) {
    ReportRow {
        ReportColumn {
            TitleText("Is CRL?")
            Row {
                SelectionPill(
                    modifier = Modifier,
                    selectableList = listOf(
                        Selectable(true, "Yes"),
                        Selectable(false, "No"),
                    ),
                    defaultValue = uiHandler.isCRL.value,
                    onSelectionChanged = { uiHandler.isCRL.value = it }
                )
            }
        }
        SeparatorSpacer()
        ReportColumn {
            AnimatedVisibility(
                uiHandler.isCRL.value,
                enter = fadeIn(),
                exit = fadeOut(),
            ) {
                TitleText("CRL")
            }
            AnimatedVisibility(
                uiHandler.isCRL.value,
                enter = fadeIn(),
                exit = fadeOut(),
            ) {
                MeasurableUnitFields(
                    measurableUnit = report.CRL,
                    firstText = uiHandler.CRL.value,
                    onFirstTextChanged = { uiHandler.CRL.value = it },
                    firstPlaceholderText = "CRL",
                )
            }
        }
    }
    AnimatedVisibility(
        !uiHandler.isCRL.value,
        enter = expandVertically() + fadeIn(),
        exit = shrinkVertically() + fadeOut()
    ) {
        ReportRow {
            ReportColumn {
                TitleText("BPD")
                Row {
                    MeasurableUnitFields(
                        measurableUnit = report.BPD,
                        firstText = uiHandler.BPD.value,
                        onFirstTextChanged = { uiHandler.BPD.value = it },
                        firstPlaceholderText = "BPD"
                    )
                }
            }
            SeparatorSpacer()
            ReportColumn {
                TitleText("FL")
                Row {
                    MeasurableUnitFields(
                        measurableUnit = report.FL,
                        firstText = uiHandler.FL.value,
                        onFirstTextChanged = { uiHandler.FL.value = it },
                        firstPlaceholderText = "FL"
                    )
                }
            }
        }
    }
    AnimatedVisibility(
        !uiHandler.isCRL.value,
        enter = expandVertically() + fadeIn(),
        exit = shrinkVertically() + fadeOut()
    ) {
        ReportRow {
            ReportColumn {
                TitleText("Presentation")
                Row {
                    SelectionPill(
                        selectableList = Presentation.getSelectableList(),
                        defaultValue = uiHandler.presentation.value,
                        onSelectionChanged = {
                            uiHandler.presentation.value = it
                        }
                    )
                }
            }
            SeparatorSpacer()
            ReportColumn {
                TitleText("Fetal Weight")
                Row {
                    MeasurableUnitFields(
                        measurableUnit = report.fetalWeight,
                        firstText = uiHandler.fetalWeight.value,
                        onFirstTextChanged = { uiHandler.fetalWeight.value = it },
                        firstPlaceholderText = "Weight",
                        secondText = uiHandler.fetalWeightRange.value,
                        onSecondTextChanged = { uiHandler.fetalWeightRange.value = it },
                        secondPlaceholderText = "Range"
                    )
                }
            }
        }
    }
    ReportRow {
        ReportColumn {
            TitleText("Placenta")
            SelectionPill(
                selectableList = Area.getAreaList(),
                defaultValue = uiHandler.placentaArea.value,
                onSelectionChanged = { uiHandler.placentaArea.value = it }
            )
            Spacer(Modifier.height(2.dp))
            SelectionPill(
                selectableList = Positioning.getPositioningList(),
                defaultValue = uiHandler.placentaPositioning.value,
                onSelectionChanged = { uiHandler.placentaPositioning.value = it }
            )
        }
        SeparatorSpacer()
        ReportColumn {
            TitleText("Amniotic Fluid")
            SelectionPill(
                selectableList = listOf(
                    Selectable(true, "Adequate"),
                    Selectable(false, "Not Adequate"),
                ),
                defaultValue = uiHandler.amnioticFluidAdequate.value,
                onSelectionChanged = { uiHandler.amnioticFluidAdequate.value = it }
            )
        }
    }
    ReportRow {
        ReportColumn {
            TitleText("Fetal Movement")
            SelectionPill(
                selectableList = listOf(
                    Selectable(true, "Normal"),
                    Selectable(false, "Not Normal"),
                ),
                defaultValue = uiHandler.fetalMovementNormal.value,
                onSelectionChanged = { uiHandler.fetalMovementNormal.value = it }
            )
        }
        SeparatorSpacer()
        ReportColumn {
            TitleText("Fetal Age")
            MeasurableUnitFields(
                measurableUnit = report.fetalAge,
                firstText = uiHandler.fetalAgeWeeks.value,
                firstPlaceholderText = "Weeks",
                onFirstTextChanged = {
                    uiHandler.fetalAgeWeeks.value = it.take(2)
                    uiHandler.calculateEDD()
                },
                secondText = uiHandler.fetalAgeDays.value,
                secondPlaceholderText = "Days",
                onSecondTextChanged = {
                    uiHandler.fetalAgeDays.value = it.take(1)
                    uiHandler.calculateEDD()
                }
            )
        }
    }
    ReportRow {
        ReportColumn {
            TitleText("Cardiac Activity")
            SelectionPill(
                selectableList = listOf(
                    Selectable(true, "Present"),
                    Selectable(false, "Not Present"),
                ),
                defaultValue = uiHandler.cardiacActivityPresent.value,
                onSelectionChanged = { uiHandler.cardiacActivityPresent.value = it }
            )
        }
        SeparatorSpacer()
        ReportColumn {
            AnimatedVisibility(
                !uiHandler.isCRL.value,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                TitleText("EDD")
            }
            AnimatedVisibility(
                !uiHandler.isCRL.value,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                MeasurableUnitFields(
                    measurableUnit = report.estimatedDeliveryDate,
                    firstText = uiHandler.estimatedDeliveryDate.value,
                    firstPlaceholderText = "EDD",
                    onFirstTextChanged = { uiHandler.estimatedDeliveryDate.value = it },
                )
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PersonalInformation(uiHandler: PregnancyReportUiHandler, report: PregnancyReport) {
    ReportRow {
        ReportColumn {
            TitleText("Name")
            Row {
                CustomTextField(
                    modifier = Modifier,
                    text = uiHandler.name.value,
                    placeholderText = "Name",
                    onTextChanged = { uiHandler.name.value = it },
                    filterType = FilterType.Text,
                )
            }
        }
        SeparatorSpacer()
        ReportColumn {
            TitleText("Age")
            Row {
                MeasurableUnitFields(
                    firstModifier = Modifier,
                    measurableUnit = report.age,
                    firstText = uiHandler.age.value,
                    onFirstTextChanged = { uiHandler.age.value = it },
                    firstPlaceholderText = "Age",
                )
            }
        }
    }
}

@Composable
fun SeparatorSpacer() {
    Spacer(Modifier.width(10.dp))
}
