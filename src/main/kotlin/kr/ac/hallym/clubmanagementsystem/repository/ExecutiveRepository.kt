package kr.ac.hallym.clubmanagementsystem.repository

import kr.ac.hallym.clubmanagementsystem.database.Database
import kr.ac.hallym.clubmanagementsystem.model.Club
import kr.ac.hallym.clubmanagementsystem.model.Executive

class ExecutiveRepository {
    fun save(entity: Executive) {
        val connection = Database.getConnection()!!

        val query = connection.prepareStatement("INSERT INTO executives VALUES (?, ?, ?)")

        query.setInt(1, entity.cid)
        query.setString(2, entity.username)
        query.setString(3, entity.password)
        query.execute()
    }

    fun findBy(club: Club, username: String): Executive? {
        val connection = Database.getConnection()!!

        val query = connection.prepareStatement("SELECT * FROM executives WHERE cid=? AND username=?")
        query.setInt(1, club.cid!!)
        query.setString(2, username)
        val rs = query.executeQuery()
        if (rs.next()) {
            return Executive(
                cid = club.cid,
                username = username,
                password = rs.getString("password")
            )
        }
        return null
    }

    fun login(club: Club, username: String, password: String): Boolean {
        return findBy(club, username)?.password == password
    }
}