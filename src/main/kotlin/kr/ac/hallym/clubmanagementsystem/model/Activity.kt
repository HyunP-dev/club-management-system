package kr.ac.hallym.clubmanagementsystem.model

import java.time.LocalDateTime

data class Activity(val aid: Int?, val cid: Int,
                    val name: String, val location: String,
                    val start: LocalDateTime, val end: LocalDateTime) {
    fun isAvailable(time: LocalDateTime): Boolean {
        return start.isBefore(time).and(end.isAfter(time))
    }

    fun remove() {
        if (aid == null) {
            throw Exception("서버에 등록되어 있지 않는 활동입니다.")
        }

    }
}
