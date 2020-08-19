package dbclient.vsch

import com.vladsch.kotlin.jdbc.Session
import com.vladsch.kotlin.jdbc.SqlQuery
import com.vladsch.kotlin.jdbc.session
import com.vladsch.kotlin.jdbc.sqlQuery
import config.ApplicationConfig
import config.ConfigSource
import config.ConfigurationProvider
import dbclient.DbClient
import dbclient.vsch.ResultSetConverter.resultSetFirstRowToMap
import dbclient.vsch.ResultSetConverter.resultSetToList

class VschClient : DbClient {
  private lateinit var config: ApplicationConfig
  private var session: Session? = null

  override fun connectToDb(): Session {
    config = ConfigurationProvider.setConfigType(ConfigSource.YAML).getConfig()
    if (session == null) {
      session = session("jdbc:mysql://${config.dbUrl}/${config.dbSchema}", config.dbUser, config.dbPassword)
      //session = session("jdbc:mysql://${config.dbUrl}/${config.dbSchema}?autoReconnect=true&useSSL=false", config.dbUser, config.dbPassword)
    }
    return session as Session
  }

  fun selectFirstRow(sqlQueryRaw: String, queryParams: Map<String, Any>): Map<String, Any?> {
    val sqlQuery: SqlQuery = buildSqlQuery(sqlQueryRaw, queryParams)
    return connectToDb().query(sqlQuery, resultSetFirstRowToMap)
  }

  fun selectAllRows(sqlQueryRaw: String, queryParams: Map<String, Any>): List<Map<String, Any?>> {
    val sqlQuery: SqlQuery = buildSqlQuery(sqlQueryRaw, queryParams)
    return connectToDb().query(sqlQuery, resultSetToList)
  }

  private fun buildSqlQuery(sqlQueryRaw: String, queryParams: Map<String, Any?>): SqlQuery {
    return if (queryParams.isNullOrEmpty()) {
      SqlQuery(sqlQueryRaw)
    } else {
      var sqlQuery: SqlQuery = sqlQuery(sqlQueryRaw)
      queryParams.forEach { (paramName, paramValue) ->
        sqlQuery = sqlQuery.inParams(paramName to paramValue)
      }
      sqlQuery
    }
  }

  override fun closeConnectToDb() {
    if (session != null) {
      connectToDb().close()
      session = null
    }
  }
}