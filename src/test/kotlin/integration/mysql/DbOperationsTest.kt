package integration.mysql

import dbclient.DbClient
import dbclient.DbConnectionProvider
import dbclient.ExposedDbClient
import dbclient.tables.BorrowerReference
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class DbOperationsTest {
  private val id = 14555
  private val dbClient: DbClient = DbConnectionProvider(ExposedDbClient())

  @Test
  fun getBorrowerRefEntitiesById() {
    dbClient.connectToDb()

    val borrowerRef = dbClient.selectBorrowerRefById(id) as BorrowerReference
    assertAll(
        { Assertions.assertEquals("MOTHER", borrowerRef.reference_type) },
        { Assertions.assertEquals("KRSkxUH2gU/r2dkyAyhSsQ==", borrowerRef.reference_phone) },
        { Assertions.assertEquals("Maria", borrowerRef.reference_name) },
        { Assertions.assertEquals(19500, borrowerRef.borrower_id) }
    )
  }
}