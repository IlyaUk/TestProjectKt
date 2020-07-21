package dbservices

import dbclient.DbClient
import dbclient.vsch.VschClient

open class MySqlOperations {
  fun getBorrowerRefDataById(id: Long): Map<String, Any?> {
    val getBorrowerRefDataQuery: String = """
    select *
    from borrower_reference
    where id = :id
    order by id desc
  """.trimIndent()
    val queryParams = mapOf("id" to id)

    return VschClient().selectFirstRow(getBorrowerRefDataQuery, queryParams)
  }

  fun getBorrowerRefDataByRefType(refType: String): List<Map<String, Any?>> {
    val getBorrowerRefDataQuery: String = """
    select id, reference_type, reference_phone, reference_name, borrower_id 
    from borrower_reference
    where reference_type = :refType
    order by id asc
    limit 2
  """.trimIndent()
    val queryParams = mapOf("reference_type" to refType)

    return VschClient().selectAllRows(getBorrowerRefDataQuery, queryParams)
  }

  fun getBorrowerRefDataAndFileContentType(borrowerId: Long, fileContentType: String): Map<String, Any?> {
    val getBorrowerRefDataQuery: String = """
    select br.id, br.reference_type, br.reference_phone, br.reference_name, br.borrower_id, bf.borrower_file_type
    from borrower_reference br
    join borrower_file_content bf on br.borrower_id = bf.borrower_id
    where br.borrower_id = :borrowerId
    and bf.borrower_file_type = :fileContentType
  """.trimIndent()
    val queryParams = mapOf("borrower_id" to borrowerId, "borrower_file_content" to fileContentType)

    return VschClient().selectFirstRow(getBorrowerRefDataQuery, queryParams)
  }

  fun closeDbConnection(client: DbClient) {
    client.closeConnectToDb()
  }
}