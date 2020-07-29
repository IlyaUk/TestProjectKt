package junitconditions

import httpclient.okhttp.OkHttp
import org.junit.jupiter.api.extension.ConditionEvaluationResult
import org.junit.jupiter.api.extension.ConditionEvaluationResult.disabled
import org.junit.jupiter.api.extension.ConditionEvaluationResult.enabled
import org.junit.jupiter.api.extension.ExecutionCondition
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.platform.commons.util.AnnotationUtils.findAnnotation
import java.lang.String.format
import java.lang.reflect.AnnotatedElement
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.annotation.AnnotationTarget.FUNCTION

@Target(FUNCTION, CLASS)
@Retention(AnnotationRetention.RUNTIME)
@ExtendWith(EnabledIfReachableCondition::class)
annotation class EnabledIfReachable(val url: String, val timeoutMillis: Long)

internal class EnabledIfReachableCondition : ExecutionCondition {
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
    val reachable: Boolean = OkHttp().isSpecifiedUrlReachable(url, timeoutMillis, responseCodesRange)
    return if (reachable) enabled(format(
        "%s is enabled because %s is reachable",
        element, url)) else disabled(format(
        "%s is disabled because %s could not be reached in %dms",
        element, url, timeoutMillis))
  }
}