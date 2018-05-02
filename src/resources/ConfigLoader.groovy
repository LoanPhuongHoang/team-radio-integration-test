package resources

class ConfigLoader {
	static Properties loadConfigurations() {
		Properties properties = new Properties()
		properties.load(getClass().getResourceAsStream('resources/configuration.properties') as InputStream)

		properties
	}
}
