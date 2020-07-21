package dbclient

class DbConnectionProvider(private val dbClient: DbClient) : DbClient {
  override fun connectToDb(): Any {
    return dbClient.connectToDb()
  }

  override fun closeConnectToDb(): Any {
    return dbClient.closeConnectToDb()
  }
}