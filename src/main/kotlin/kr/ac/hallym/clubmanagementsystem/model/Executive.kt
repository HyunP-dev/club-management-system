package kr.ac.hallym.clubmanagementsystem.model

import java.time.LocalDateTime

data class Executive(val cid: Int, val username: String, val password: String) {
    fun addActivity(name: String, location: String, start: LocalDateTime, end: LocalDateTime) {

    }
}
