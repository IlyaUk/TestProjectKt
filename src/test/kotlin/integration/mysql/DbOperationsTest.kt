package integration.mysql

import dbclient.DbClient
import dbclient.DbConnectionProvider
import dbclient.exposedclient.ExposedDbClient
import dbclient.exposedclient.borrower_reference
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.Query
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class DbOperationsTest {
  private val id = 14555
  private val table = borrower_reference
  private val query: Op<Boolean> = table.borrower_id eq id
  private val dbClient: DbClient = DbConnectionProvider(ExposedDbClient())

  @Test
  fun getBorrowerRefEntitiesById() {
    dbClient.connectToDb()

    val borrowerRef: Query = ExposedDbClient().selectTableData(table, { query })
    println(borrowerRef)
  }

  @Test
  @Disabled
  fun getDataUsingJoin() {
    dbClient.connectToDb()

    val data = ExposedDbClient().selectTableDataWithJoin()
    println(data)
  }
}