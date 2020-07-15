package dbclient

import config.ApplicationConfig
import config.ConfigSource
import config.ConfigurationProvider
import dbclient.tables.BorrowerReference
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

class ExposedDbClient : DbClient {
  private lateinit var config: ApplicationConfig

  override fun connectToDb(): Database {
    config = ConfigurationProvider.setConfigType(ConfigSource.YAML).getConfig()
    return Database.connect("jdbc:mysql://${config.dbUrl}/${config.dbSchema}", "com.mysql.jdbc.Driver", config.dbUser,
        config.dbPassword)
  }

  override fun selectBorrowerRefById(id: Int): BorrowerReference {
    return transaction {
      addLogger(StdOutSqlLogger)
      BorrowerReference.findById(id)!!
    }
  }
}