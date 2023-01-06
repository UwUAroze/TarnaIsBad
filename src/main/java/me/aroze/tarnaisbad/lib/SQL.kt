package me.aroze.tarnaisbad.lib

import org.bukkit.Bukkit
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet
import me.aroze.tarnaisbad.TarnaIsBad

object SQL {
    private val conn: Connection

    init {
        val plugin = TarnaIsBad.getInstance()

        conn = DriverManager.getConnection("jdbc:sqlite:${plugin.dataFolder}/data.db")

        execute("""CREATE TABLE IF NOT EXISTS warps(
            name TEXT NOT NULL PRIMARY KEY UNIQUE,
            location TEXT NOT NULL
        );""".trimIndent())

        execute("""CREATE TABLE IF NOT EXISTS homes(
            name TEXT NOT NULL PRIMARY KEY UNIQUE,
            owner TEXT NOT NULL,
            location TEXT NOT NULL
        )""".trimIndent())
    }
    
    fun execute(sql: String, vararg values: Any) {
        prepare(sql, *values).execute()
    }
    
    fun query(sql: String, vararg values: Any): ResultSet {
        return prepare(sql, *values).executeQuery()
    }
    
    private fun prepare(sql: String, vararg values: Any): PreparedStatement {
        val stmt = conn.prepareStatement(sql)
        
        values.forEachIndexed { index, value ->
            stmt.setObject(index + 1, value)
        }
        
        return stmt
    }
}