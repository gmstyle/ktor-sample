package com.example.database

import com.typesafe.config.ConfigFactory
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.config.*
import org.flywaydb.core.Flyway
import org.ktorm.database.Database
import java.io.PrintWriter
import java.util.*

object DatabaseConnector {


    //carico le proprietà di base configurate nel file application.conf
    private val appConfig = HoconApplicationConfig(ConfigFactory.load())

    //setto le props con le proprietà specifiche per mysql
    private val props = Properties().apply {
        setProperty("cachePrepStmts", "true")
        setProperty("prepStmtCacheSize", "250")
        setProperty("prepStmtCacheSqlLimit", "2048")
        setProperty("useServerPrepStmts", "true")
        setProperty("useLocalSessionState", "true")
        setProperty("rewriteBatchedStatements", "true")
        setProperty("cacheResultSetMetadata", "true")
        setProperty("cacheServerConfiguration", "true")
        setProperty("elideSetAutoCommits", "true")
        setProperty("maintainTimeStats", "false")
        put("logWriter", PrintWriter(System.out))
    }
    private val dbConfig = HikariConfig().apply {

        driverClassName = appConfig.property("ktor.datasource.driver").getString()
        username = appConfig.property("ktor.datasource.username").getString()
        password = appConfig.property("ktor.datasource.password").getString()
        jdbcUrl = appConfig.property("ktor.datasource.jdbcUrl").getString()
        schema = appConfig.property("ktor.datasource.schema").getString()
        dataSourceProperties = props
    }
    private val dataSource = HikariDataSource(dbConfig)
    private val flyway = Flyway.configure()
        .dataSource(dataSource)
        .load()
        .migrate()

    val database = Database.connect(dataSource)
}

