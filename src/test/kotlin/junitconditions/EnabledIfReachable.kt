package junitconditions

import httpclient.okhttp.OkHttp
import okhttp3.Request
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.junit.jupiter.api.extension.ConditionEvaluationResult
import org.junit.jupiter.api.extension.ConditionEvaluationResult.disabled
import org.junit.jupiter.api.extension.ConditionEvaluationResult.enabled
import org.junit.jupiter.api.extension.ExecutionCondition
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.platform.commons.util.AnnotationUtils.findAnnotation
import java.io.IOException
import java.lang.reflect.AnnotatedElement
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.annotation.AnnotationTarget.FUNCTION

@Target(FUNCTION, CLASS)
@Retention(AnnotationRetention.RUNTIME)
@ExtendWith(EnabledIfReachableCondition::class)
annotation class EnabledIfReachable(val url: String, val timeoutMillis: Long)

internal class EnabledIfReachableCondition : ExecutionCondition {
  private val log: Logger = LogManager.getLogger(EnabledIfReachableCondition::class.simpleName)
  private val responseCodesRange: IntRange = 200..399

  companion object {
    private val ENABLED_BY_DEFAULT: ConditionEvaluationResult = enabled("@EnabledIfReachable is not present")
  }

  override fun evaluateExecutionCondition(context: ExtensionContext): ConditionEvaluationResult {
    val element = context
        .element
        .orElseThrow { IllegalStateException() }
    return findAnnotation(element, EnabledIfReachable::class.java)
        .map { annotation -> disableIfUnreachable(annotation, element) }
        .orElse(ENABLED_BY_DEFAULT)
  }

  private fun disableIfUnreachable(annotation: EnabledIfReachable,
      element: AnnotatedElement): ConditionEvaluationResult {
    val url = annotation.url
    val timeoutMillis = annotation.timeoutMillis
    val reachable: Boolean = isSpecifiedUrlReachable(url, timeoutMillis, responseCodesRange)
    return if (reachable) enabled(
        "$element is enabled because $url is reachable")
    else disabled(
        "$element is disabled because $url could not be reached in $timeoutMillis")
  }

  private fun isSpecifiedUrlReachable(url: String, timeout: Long, responseCodesRange: IntRange): Boolean {
    return try {
      val request = Request.Builder()
          .url(url)
          .build()
      val response = OkHttp().sendGetRequest(request, timeout)
      val responseCode = response.code
      OkHttp().closeResponse(response)
      responseCode in responseCodesRange
    } catch (exception: IOException) {
      log.error("The remote host $url is not available. Error: ${exception.message}")
      false
    }
  }
}