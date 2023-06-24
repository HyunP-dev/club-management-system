package kr.ac.hallym.clubmanagementsystem.model

import java.time.LocalDateTime

data class Activity(val aid: Int, val cid: Int,
                    val name: String, val location: String,
                    val start: LocalDateTime, val end: LocalDateTime) {
    fun isAvailable(time: LocalDateTime): Boolean {
        return start.isBefore(time).and(end.isAfter(time))
    }
}
