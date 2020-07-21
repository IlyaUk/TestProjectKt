package dbclient

interface DbClient {
  fun connectToDb(): Any
  fun closeConnectToDb(): Any
}