package kr.ac.hallym.clubmanagementsystem.model

import kr.ac.hallym.clubmanagementsystem.repository.ActivityRepository
import java.time.LocalDateTime

data class Executive(val cid: Int,
                     val username: String,
                     val password: String) {
    fun addActivity(name: String, location: String, start: LocalDateTime, end: LocalDateTime) {
        val activityRepository = ActivityRepository()
        activityRepository.save(Activity(
            aid = null,
            cid = cid,
            name = name,
            location = location,
            start = start,
            end = end
        ))
    }
}
