package dbclient

interface DbClient {
  fun connectToDb(): Any
  fun selectBorrowerRefById(id: Int): Any
}