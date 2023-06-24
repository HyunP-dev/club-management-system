package kr.ac.hallym.clubmanagementsystem.repository

import kr.ac.hallym.clubmanagementsystem.database.Database
import kr.ac.hallym.clubmanagementsystem.model.Attendance
import kr.ac.hallym.clubmanagementsystem.model.Member

class MemberRepository {
    fun findAll(): List<Member> {
        val connection = Database.getConnection()!!

        val query = connection.createStatement()
        val rs = query.executeQuery("SELECT * FROM members")
        val results: ArrayList<Member> = ArrayList()
        while (rs.next()) {
            results.add(
                Member(
                    mid = rs.getInt("mid"),
                    name = rs.getString("name"),
                    cid = rs.getInt("cid")
                )
            )
        }
        return results
    }
}