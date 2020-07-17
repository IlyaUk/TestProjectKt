package dbclient.exposedclient

import config.ApplicationConfig
import config.ConfigSource
import config.ConfigurationProvider
import dbclient.DbClient
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class ExposedDbClient : DbClient {
  private lateinit var config: ApplicationConfig

  override fun connectToDb(): Database {
    config = ConfigurationProvider.setConfigType(ConfigSource.YAML).getConfig()
    return Database.connect("jdbc:mysql://${config.dbUrl}/${config.dbSchema}", "com.mysql.jdbc.Driver", config.dbUser,
        config.dbPassword)
  }

  fun selectTableData(table: Table, expression: () -> Op<Boolean>): Query {
    return transaction {
      addLogger(StdOutSqlLogger)
      table.select { expression.invoke() }
    }
  }

  fun selectTableDataWithJoin(): List<*> {
    val query = borrower_reference.join(borrower_file_content, JoinType.INNER, borrower_reference.borrower_id,
        borrower_file_content.borrower_id)
        .select {
          borrower_reference.id eq 2 and (borrower_file_content.id eq 467)
        }
    return query.toList()
  }
}