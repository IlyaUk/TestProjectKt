package smoke

import config.ConfigSource
import config.ConfigurationProvider
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class CreateConfigDynamicTest {
  @ParameterizedTest
  @EnumSource(names = ["YAML", "JSON"])
  fun CreateConfigObject(source: ConfigSource) {
    val configurationObject = ConfigurationProvider.setConfigType(source).getConfig()
    assertAll("configurationObjectJSON",
        { assertEquals("moneyman", configurationObject.user) },
        { assertEquals(100500, configurationObject.pass) },
        { assertEquals("qa-delivery-master.mm.ru", configurationObject.host) },
        { assertEquals("/client-area/registration", configurationObject.registrationServiceEndpoint) }
    )
  }
}
