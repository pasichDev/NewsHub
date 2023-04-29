package com.pasichdev.newshub.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun convertToNewFormat(dateStr: String): String {
    val currentFormatter = DateTimeFormatter.ISO_DATE_TIME
    val newFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
    val dateTime = LocalDateTime.parse(dateStr, currentFormatter)
    return dateTime.format(newFormatter)
}