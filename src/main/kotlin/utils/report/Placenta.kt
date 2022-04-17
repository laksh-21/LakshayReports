package utils.report

import utils.ui.Selectable

data class Placenta(val area: Area, val position: Positioning) {
    fun getFormattedString(): String {
        return "Placenta: ${area.name} ${position.name} part"
    }
}

sealed class Area(val value: Int, val name: String) {
    object Anterior : Area(0, "anterior")
    object Posterior : Area(1, "posterior")

    companion object {
        fun getAreaList(): List<Selectable<Area>> {
            return listOf(
                Selectable(Anterior, Anterior.name),
                Selectable(Posterior, Posterior.name),
            )
        }
    }
}

sealed class Positioning(val value: Int, val name: String) {
    object Upper : Positioning(0, "upper")
    object Middle : Positioning(1, "middle")
    object Lower : Positioning(2, "lower")

    companion object {
        fun getPositioningList(): List<Selectable<Positioning>> {
            return listOf(
                Selectable(Upper, Upper.name),
                Selectable(Middle, Middle.name),
                Selectable(Lower, Lower.name),
            )
        }
    }
}
