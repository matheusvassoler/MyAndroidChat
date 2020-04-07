package br.com.havebreak.util

import android.text.BoringLayout
import java.util.*

class DateUtil {

    companion object {
        private fun formatHourTo24HourFormat(hour: Int, minute: Int): String {
            return String.format("%02d:%02d", hour, minute)
        }

        private fun formatHourTo12HourFormat(hour: Int, minute: Int, amPm: Int): String {
            var hour = hour
            if(hour == 0) hour = 12

            if (amPm == 0) {
                return String.format("%02d:%02d", hour, minute) + "AM"
            } else {
                return String.format("%02d:%02d", hour, minute) + "PM"
            }
        }

        fun getActualEpochTimestampSeconds(): Long {
            return System.currentTimeMillis() / 1000
        }

        fun convertEpochTimestampSecondsToDate(epochTimetampSeconds: Long): Date {
            return Date(epochTimetampSeconds * 1000L)
        }

        fun formatHour(calendar: Calendar, is24HourFormat: Boolean): String {
            if(is24HourFormat) {
                val hour = calendar.get(Calendar.HOUR_OF_DAY)
                val minute = calendar.get(Calendar.MINUTE)
                return formatHourTo24HourFormat(hour, minute)
            } else {
                val hour = calendar.get(Calendar.HOUR)
                val minute = calendar.get(Calendar.MINUTE)
                val amPm = calendar.get(Calendar.AM_PM)
                return formatHourTo12HourFormat(hour, minute, amPm)
            }
        }
    }

}