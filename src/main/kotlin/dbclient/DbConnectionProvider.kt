package dbclient

class DbConnectionProvider(dbClient: DbClient) : DbClient {
  private var client: DbClient = dbClient

  override fun connectToDb(): Any {
    return client.connectToDb()
  }
}