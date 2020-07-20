package dbclient.exposedclient

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object borrower_file_content : IntIdTable() {
  val content_type: Column<String> = varchar("content_type", 20)
  val upload_date: Column<String> = varchar("upload_date", 20)
  val borrower_file_type: Column<String> = varchar("borrower_file_type", 100)
  val file_uuid: Column<String> = varchar("file_uuid", 64)
  val deleted: Column<Int> = integer("deleted")
  val borrower_id: Column<Int> = integer("borrower_id").uniqueIndex()
  override val primaryKey = PrimaryKey(borrower_id)
}