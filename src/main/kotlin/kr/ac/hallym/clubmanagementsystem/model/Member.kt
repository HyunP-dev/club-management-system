package kr.ac.hallym.clubmanagementsystem.model

import kr.ac.hallym.clubmanagementsystem.repository.AttendanceRepository
import java.time.LocalDateTime

data class Member(val mid: Int?, val name: String, val cid: Int) {
    fun attend(activity: Activity) {
        val attendanceRepository = AttendanceRepository()
        val now = LocalDateTime.now()
        if (activity.isAvailable(now)) {
            attendanceRepository.save(
                Attendance(
                aid = activity.aid!!,
                mid = mid!!
            )
            )
        }
    }
}
