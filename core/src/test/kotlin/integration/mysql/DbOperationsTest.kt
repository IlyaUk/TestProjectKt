package integration.mysql

import dbclient.DbClient
import dbclient.DbConnectionProvider
import dbclient.vsch.VschClient
import dbservices.MySqlOperations
import org.junit.jupiter.api.*

class DbOperationsTest : MySqlOperations() {
  private val dbClient: DbClient = DbConnectionProvider(VschClient())
  private val id: Long = 1
  private val referenceType = "FATHER"
  private val borrowerId: Long = 14555
  private val borrowerFileType = "AVERSE_INE"

  @AfterEach
  fun closeConnection() {
    closeDbConnection(dbClient)
  }

  @Test
  fun getBorrowerReferenceOneRowData() {
    val resultMap = getBorrowerRefDataById(id)

    assertAll(
        { Assertions.assertEquals(id, resultMap["id"]) },
        { Assertions.assertEquals("FATHER", resultMap["reference_type"]) },
        { Assertions.assertEquals("wer", resultMap["reference_phone"]) },
        { Assertions.assertEquals("wer", resultMap["reference_name"]) },
        { Assertions.assertEquals(5137L, resultMap["borrower_id"]) }
    )
  }

  @Test
  fun getBorrowerReferenceMultipleRowsData() {
    val resultList = getBorrowerRefDataByRefType(referenceType)

    assertAll(
        { Assertions.assertTrue(resultList.size == 2) },
        { Assertions.assertEquals(1L, resultList[0]["id"]) },
        { Assertions.assertEquals("FATHER", resultList[0]["reference_type"]) },
        { Assertions.assertEquals("wer", resultList[0]["reference_phone"]) },
        { Assertions.assertEquals("wer", resultList[0]["reference_name"]) },
        { Assertions.assertEquals(5137L, resultList[0]["borrower_id"]) }
    )
  }

  @Test
  fun getBorrowerReferenceAndFileContentData() {
    val resultMap = getBorrowerRefDataAndFileContentType(borrowerId, borrowerFileType)

    assertAll(
        { Assertions.assertEquals(9737L, resultMap["id"]) },
        { Assertions.assertEquals("MOTHER", resultMap["reference_type"]) },
        { Assertions.assertEquals("98Key3+Qpzbi9ohakFU4iA==", resultMap["reference_phone"]) },
        { Assertions.assertEquals("wewe", resultMap["reference_name"]) },
        { Assertions.assertEquals(14555L, resultMap["borrower_id"]) },
        { Assertions.assertEquals("AVERSE_INE", resultMap["borrower_file_type"]) }
    )
  }
}