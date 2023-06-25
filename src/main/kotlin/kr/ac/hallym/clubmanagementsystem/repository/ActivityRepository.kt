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

    fun updateByAid(aid: Int, entity: Activity) {
        val connection = Database.getConnection()!!

        val query = connection.prepareStatement("UPDATE activities SET cid=?, name=?, location=?, start=?, end=? WHERE aid=?")

        query.setInt(1, entity.cid)
        query.setString(2, entity.name)
        query.setString(3, entity.location)
        query.setTimestamp(4, Timestamp.valueOf(entity.start))
        query.setTimestamp(5, Timestamp.valueOf(entity.end))
        query.setInt(6, entity.aid!!)
        query.execute()
    }

    fun deleteByAid(aid: Int) {
        val connection = Database.getConnection()!!

        val query = connection.prepareStatement("DELETE FROM activities WHERE aid = ?")

        query.setInt(1, aid)
        query.execute()
    }

    fun findAll(): List<Activity> {
        val connection = Database.getConnection()!!

        val query = connection.createStatement()
        val rs = query.executeQuery("SELECT * FROM activities")
        val results: ArrayList<Activity> = ArrayList()
        while (rs.next()) {
            results.add(
                Activity(
                    aid = rs.getInt("aid"),
                    cid = rs.getInt("cid"),
                    name = rs.getString("name"),
                    location = rs.getString("location"),
                    start = rs.getTimestamp("start").toLocalDateTime(),
                    end = rs.getTimestamp("end").toLocalDateTime()
                )
            )
        }
        return results
    }
    fun findBy(aid: Int): Activity? {
        val connection = Database.getConnection()!!

        val query = connection.prepareStatement("SELECT * FROM activities WHERE aid=?")
        query.setInt(1, aid)
        val rs = query.executeQuery()
        if (rs.next()) {
            return Activity(
                aid = aid,
                cid = rs.getInt("cid"),
                name = rs.getString("name"),
                location = rs.getString("location"),
                start = rs.getTimestamp("start").toLocalDateTime(),
                end = rs.getTimestamp("end").toLocalDateTime()
            )
        }
        return null
    }
}
