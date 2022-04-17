package utils

import java.text.SimpleDateFormat
import java.util.*

object Utils {
    private const val DATE_FORMAT = "dd/MM/yyyy"
    private const val FOLDER_DATE_FORMAT = "dd-MM-yyyy"
    fun getTodayDate(): String {
        val format = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
        return format.format(Date())
    }

    fun getFolderDate(date: Date): String {
        val format = SimpleDateFormat(FOLDER_DATE_FORMAT, Locale.getDefault())
        return format.format(date)
    }

    fun getDateAhead(weeks: Int, days: Int): String {
        val today = Calendar.getInstance()
        val dateAhead = today.apply {
            add(Calendar.DATE, (weeks * 7) + days)
        }
        return formatDate(dateAhead.time)
    }

    fun formatDate(date: Date): String {
        val format = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
        return format.format(date)
    }
}