package tare.app.test.openinapptest.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

fun String.convertToStandardDate(): String{
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    try {
        val finalStr = outputFormat.format(inputFormat.parse(this) ?: throw Exception())
        println(finalStr)
        return finalStr
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return ""
}