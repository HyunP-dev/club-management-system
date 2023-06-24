package kr.ac.hallym.clubmanagementsystem.repository

import kr.ac.hallym.clubmanagementsystem.database.Database
import kr.ac.hallym.clubmanagementsystem.model.Attendance
import kr.ac.hallym.clubmanagementsystem.model.Member

class MemberRepository {
    fun findBy(mid: Int): Member? {
        val connection = Database.getConnection()!!

        val statement = connection.prepareStatement("SELECT * FROM members WHERE mid = ?")
        statement.setInt(1, mid)
        val rs = statement.executeQuery()
        if (rs.next()) {
            return Member(
                mid = rs.getInt("mid"),
                name = rs.getString("name"),
                cid = rs.getInt("cid")
            )
        }
        return null
    }

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

    fun deleteBy(mid: Int) {
        val connection = Database.getConnection()!!

        val statement = connection.prepareStatement("DELETE FROM members WHERE mid = ?")
        statement.setInt(1, mid)
        statement.execute()
    }
}