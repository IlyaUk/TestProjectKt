package dbclient.vsch

import java.sql.ResultSet

object ResultSetConverter {
  val resultSetFirstRowToMap: (ResultSet) -> Map<String, Any?> = { resultSet ->
    val metaData = resultSet.metaData
    val columnsCount = metaData.columnCount
    val firstRowSelectData = HashMap<String, Any?>(columnsCount)
    if (resultSet.next()) {
      for (column in 1..columnsCount) {
        firstRowSelectData[metaData.getColumnName(column)] = resultSet.getObject(column)
      }
    }
    firstRowSelectData
  }

  val resultSetToList: (ResultSet) -> List<Map<String, Any?>> = { resultSet ->
    val metaData = resultSet.metaData
    val columnsCount = metaData.columnCount
    val allRowsSelectData = ArrayList<HashMap<String, Any?>>()
    while (resultSet.next()) {
      val firstRowSelectData = HashMap<String, Any?>(columnsCount)
      for (column in 1..columnsCount) {
        firstRowSelectData[metaData.getColumnName(column)] = resultSet.getObject(column)
      }
      allRowsSelectData.add(firstRowSelectData)
    }
    allRowsSelectData
  }
}