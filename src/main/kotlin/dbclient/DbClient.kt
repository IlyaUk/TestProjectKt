package dbclient

interface DbClient {
  fun connectToDb(): Any
}