package kr.ac.hallym.clubmanagementsystem.database

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

object Database {
    private var connection: Connection? = null

    fun getConnection(): Connection? {
        try {
            if (connection != null && connection!!.isClosed) return connection
        } catch (ignored: SQLException) {
        }
        val url = "jdbc:mariadb://localhost:3306/club_management_system"
        try {
            Class.forName("org.mariadb.jdbc.Driver")
            connection = DriverManager.getConnection(url, "cmanager", "")
        } catch (exception: SQLException) {
            System.err.println(exception.message)
            return null
        } catch (exception: ClassNotFoundException) {
            System.err.println(exception.message)
            return null
        }
        return connection
    }
}