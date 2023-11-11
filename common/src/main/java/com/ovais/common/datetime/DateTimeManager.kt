package com.ovais.common.datetime

import android.os.Build
import android.text.format.DateUtils
import androidx.annotation.RequiresApi
import com.ovais.common.utils.default
import java.time.Instant
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.concurrent.TimeUnit
import javax.inject.Inject

sealed interface Day {
    object Yesterday : Day
    object Today : Day
    object Tomorrow : Day
    data class Other(val date: String) : Day
}

interface DateTimeManager {
    fun getDay(milliseconds: Long): Day
    fun getTime(milliseconds: Long): String
    fun getDate(milliseconds: Long): String
    fun getDateTime(milliseconds: Long): String
    fun getFullDateTime(milliseconds: Long): String
    fun getFullDate(milliseconds: Long): String
    fun getMediumDate(milliseconds: Long): String
    fun getCurrentTimeInMillis(): Long
    fun getDifferenceInMinutes(old: Long, new: Long): Long
    fun getDifferenceInSeconds(old: Long, new: Long): Long
    fun getDateAndMonth(milliseconds: Long): String
    fun buildDateTime(date: Long, hour: Int, minute: Int): Long
    fun formatTime(hour: Int, minute: Int): String
    fun getCurrentDate(): String
    fun getCurrentTime(): String
    fun getSeconds(milliseconds: Long): Long
    fun isToday(milliseconds: Long): Boolean
}

@RequiresApi(Build.VERSION_CODES.O)
class DefaultDateTimeManager @Inject constructor() : DateTimeManager {

    private val defaultDateTimeFormatter: DateTimeFormatter
        get() = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getDay(milliseconds: Long): Day {
        val parsedDate = getLocalDateTime(milliseconds)
        val nowDate = LocalDateTime.now()
        return when (parsedDate) {
            nowDate -> Day.Today
            nowDate.plusDays(1) -> Day.Tomorrow
            nowDate.minusDays(1) -> Day.Yesterday
            else -> Day.Other(parsedDate.format(defaultDateTimeFormatter))
        }
    }

    override fun getTime(milliseconds: Long): String {
        return getLocalDateTime(milliseconds)
            .format(DateTimeFormatter.ofPattern("hh:mm a"))
    }

    override fun getDate(milliseconds: Long): String {
        return getLocalDateTime(milliseconds)
            .format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT))
    }

    override fun getDateTime(milliseconds: Long): String {
        return getLocalDateTime(milliseconds)
            .format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT))
    }

    override fun getFullDateTime(milliseconds: Long): String {
        return Instant.ofEpochMilli(milliseconds)
            .atZone(ZoneId.systemDefault())
            .format(
                DateTimeFormatter.ofLocalizedDateTime(
                    FormatStyle.FULL, FormatStyle.SHORT
                )
            )
    }

    override fun getFullDate(milliseconds: Long): String {
        return getLocalDateTime(milliseconds)
            .format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL))
    }

    override fun getMediumDate(milliseconds: Long): String {
        return getLocalDateTime(milliseconds)
            .format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))
    }

    override fun getCurrentTimeInMillis(): Long {
        return System.currentTimeMillis()
    }

    override fun getDifferenceInMinutes(old: Long, new: Long): Long {
        return TimeUnit.MILLISECONDS.toMinutes(new - old)
    }

    override fun getDifferenceInSeconds(old: Long, new: Long): Long {
        return TimeUnit.MILLISECONDS.toSeconds(new - old)
    }

    override fun getDateAndMonth(milliseconds: Long): String {
        val fullDate: String = getLocalDateTime(milliseconds)
            .format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))
        return fullDate.split(",", ignoreCase = true)
            .firstOrNull()
            .default()
    }

    override fun buildDateTime(date: Long, hour: Int, minute: Int): Long {
        val localDate = Instant.ofEpochMilli(date)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
        return localDate.atTime(hour, minute)
            .atZone(ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli()
    }

    override fun formatTime(hour: Int, minute: Int): String {
        return LocalTime
            .of(hour, minute)
            .format(
                DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)
            )
    }

    override fun getCurrentDate(): String {
        return getDate(getCurrentTimeInMillis())
    }

    override fun getCurrentTime(): String {
        return getTime(getCurrentTimeInMillis())
    }

    override fun getSeconds(milliseconds: Long): Long {
        return TimeUnit.MILLISECONDS.toSeconds(milliseconds)
    }

    override fun isToday(milliseconds: Long): Boolean {
        return DateUtils.isToday(milliseconds)
    }

    private fun getLocalDateTime(milliseconds: Long): LocalDateTime {
        return Instant.ofEpochMilli(milliseconds)
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime()
    }
}