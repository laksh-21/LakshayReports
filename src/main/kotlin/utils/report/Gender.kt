package utils.report

sealed class Gender(val value: Int, val name: String) {
    object Male : Gender(0, "Male")
    object Female : Gender(1, "Female")
    object Other : Gender(2, "Other")
}
