package kr.ac.hallym.clubmanagementsystem.repository

import kr.ac.hallym.clubmanagementsystem.database.Database
import kr.ac.hallym.clubmanagementsystem.model.Club

class ClubRepository {
    fun save(entity: Club) {
        val connection = Database.getConnection()!!

        val query = connection.prepareStatement("INSERT INTO clubs VALUES (NULL, ?, ?)")

        query.setString(1, entity.school)
        query.setString(2, entity.name)
        query.execute()
    }

    fun findAll(): List<Club> {
        val connection = Database.getConnection()!!

        val query = connection.createStatement()
        val rs = query.executeQuery("SELECT * FROM clubs")
        val results: ArrayList<Club> = ArrayList()
        while (rs.next()) {
            results.add(
                Club(
                    cid = rs.getInt("cid"),
                    school = rs.getString("school"),
                    name = rs.getString("name")
                )
            )
        }
        return results
    }
}