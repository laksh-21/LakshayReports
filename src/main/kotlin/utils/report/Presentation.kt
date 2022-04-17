package utils.report

import utils.ui.Selectable

sealed class Presentation(val value: Int, val name: String, private val title: String = "Presentation") {
    object Cephalic : Presentation(0, "Cephalic")
    object Breech : Presentation(1, "Breech")
    object Unstable : Presentation(2, "Unstable")
    object Transverse : Presentation(3, "Transverse")

    fun getFormattedPresentation(): String {
        return "$title: $name at the time of scan."
    }

    companion object {
        fun getSelectableList(): List<Selectable<Presentation>> {
            return listOf(
                Selectable(Cephalic, Cephalic.name),
                Selectable(Breech, Breech.name),
                Selectable(Transverse, Transverse.name),
            )
        }
    }
}
