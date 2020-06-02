package config

interface ConfigFactory {
  fun getConfig(): Configuration
}