package kr.ac.hallym.clubmanagementsystem.repository

import kr.ac.hallym.clubmanagementsystem.database.Database
import kr.ac.hallym.clubmanagementsystem.model.Activity
import java.sql.Timestamp

class ActivityRepository {
    fun save(entity: Activity) {
        val connection = Database.getConnection()!!

        val query = connection.prepareStatement("INSERT INTO activities VALUES (NULL, ?, ?, ?, ?, ?)")

        query.setInt(1, entity.cid)
        query.setString(2, entity.name)
        query.setString(3, entity.location)
        query.setTimestamp(3, Timestamp.valueOf(entity.start))
        query.setTimestamp(3, Timestamp.valueOf(entity.end))
        query.execute()
    }
}