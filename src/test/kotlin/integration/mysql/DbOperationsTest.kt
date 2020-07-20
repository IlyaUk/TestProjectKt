package integration.mysql

import dbclient.DbClient
import dbclient.DbConnectionProvider
import dbclient.vsch.VschClient
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class DbOperationsTest {
  private val dbClient: DbClient = DbConnectionProvider(VschClient())
  private val id = 1
  private val referenceType = "FATHER"
  private val borrowerId = 14555
  private val borrowerFileType = "AVERSE_INE"

  @Test
  fun getBorrowerReferenceOneRowData() {
    val getBorrowerRefDataQuery: String = """
    select *
    from borrower_reference
    where id = :id
    order by id desc
  """.trimIndent()

    val resultMap: Map<String, Any?> = VschClient().selectFirstRow(
        getBorrowerRefDataQuery,
        mapOf("id" to id)
    )
    dbClient.closeConnectToDb()

    assertAll(
        { Assertions.assertEquals(id.toLong(), resultMap["id"]) },
        { Assertions.assertEquals("FATHER", resultMap["reference_type"]) },
        { Assertions.assertEquals("wer", resultMap["reference_phone"]) },
        { Assertions.assertEquals("wer", resultMap["reference_name"]) },
        { Assertions.assertEquals(5137L, resultMap["borrower_id"]) }
    )
  }

  @Test
  fun getBorrowerReferenceMultipleRowsData() {
    val getBorrowerRefDataQuery: String = """
    select id, reference_type, reference_phone, reference_name, borrower_id 
    from borrower_reference
    where reference_type = "FATHER"
    order by id asc
    limit 2
  """.trimIndent()

    val resultList: List<Map<String, Any?>> = VschClient().selectAllRows(
        getBorrowerRefDataQuery,
        mapOf("reference_type" to referenceType)
    )
    dbClient.closeConnectToDb()

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
    val getBorrowerRefDataQuery: String = """
    select br.id, br.reference_type, br.reference_phone, br.reference_name, br.borrower_id, bf.borrower_file_type
    from borrower_reference br
    join borrower_file_content bf on br.borrower_id = bf.borrower_id
    where br.borrower_id = 14555
    and bf.borrower_file_type = "AVERSE_INE"
  """.trimIndent()

    val resultMap: Map<String, Any?> = VschClient().selectFirstRow(
        getBorrowerRefDataQuery,
        mapOf("borrower_id" to borrowerId, "borrower_file_content" to borrowerFileType)
    )
    dbClient.closeConnectToDb()

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