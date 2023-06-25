package kr.ac.hallym.clubmanagementsystem.model

import kr.ac.hallym.clubmanagementsystem.repository.ActivityRepository
import kr.ac.hallym.clubmanagementsystem.repository.MemberRepository
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

    fun removeActivity(aid: Int) {
        val activityRepository = ActivityRepository()
        if (activityRepository.findBy(aid)!!.cid == cid)
            activityRepository.deleteByAid(aid)
    }

    fun kick(member: Member) {
        val memberRepository = MemberRepository()
        if (memberRepository.findBy(mid = member.mid!!)!!.cid == cid)
            memberRepository.deleteBy(mid = member.mid)
    }
}
