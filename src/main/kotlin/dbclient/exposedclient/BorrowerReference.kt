package dbclient.exposedclient

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

/*class BorrowerReference(id: EntityID<Int>) : IntEntity(id) {
  companion object : IntEntityClass<BorrowerReference>(borrower_reference)
  //var id: Int by borrower_reference.id
  var reference_type by borrower_reference.reference_type
  var reference_phone by borrower_reference.reference_phone
  var reference_name by borrower_reference.reference_name
  var borrower_id by borrower_reference.borrower_id
}*/

object borrower_reference : IntIdTable() {
  val reference_type: Column<String> = varchar("reference_type", 64)
  val reference_phone: Column<String> = varchar("reference_phone", 255)
  val reference_name: Column<String> = varchar("reference_name", 255)
  val borrower_id: Column<Int> = integer("borrower_id").uniqueIndex()
  override val primaryKey = PrimaryKey(borrower_id)
}