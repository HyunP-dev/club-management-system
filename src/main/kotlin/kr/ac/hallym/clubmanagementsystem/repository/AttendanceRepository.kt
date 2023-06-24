package kr.ac.hallym.clubmanagementsystem.repository

import kr.ac.hallym.clubmanagementsystem.database.Database
import kr.ac.hallym.clubmanagementsystem.model.Activity
import kr.ac.hallym.clubmanagementsystem.model.Attendance
import kr.ac.hallym.clubmanagementsystem.model.Club
import java.sql.Timestamp

class AttendanceRepository {
    fun save(entity: Attendance) {
        val connection = Database.getConnection()!!

        val query = connection.prepareStatement("INSERT INTO attendance VALUES (?, ?)")

        query.setInt(1, entity.aid)
        query.setInt(2, entity.mid)
        query.execute()
    }

    fun findAll(): List<Attendance> {
        val connection = Database.getConnection()!!

        val query = connection.createStatement()
        val rs = query.executeQuery("SELECT * FROM attendance")
        val results: ArrayList<Attendance> = ArrayList()
        while (rs.next()) {
            results.add(
                Attendance(aid = rs.getInt("aid"), mid = rs.getInt("mid"))
            )
        }
        return results
    }
}