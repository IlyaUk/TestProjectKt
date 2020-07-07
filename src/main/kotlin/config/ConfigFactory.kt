package config

interface ConfigFactory {
  fun getConfig(): ApplicationConfig
}