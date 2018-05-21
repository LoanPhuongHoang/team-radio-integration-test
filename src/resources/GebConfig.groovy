import groovy.transform.Field
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions
import resources.ConfigLoader

@Field
Properties config = loadConfigurations()

def browser = config.getProperty('geb.browser')

driver = {
	loadBrowserDriver(browser)
}

reportsDir = config.getProperty('geb.report.output')
reportOnTestFailureOnly = config.getProperty('geb.report.failureOnly')
baseUrl = config.getProperty('app.baseUrl')
atCheckWaiting = true
baseNavigatorWaiting = true

System.setProperty('app.user.all', config.getProperty('app.user.all'))
System.setProperty('app.user.password', config.getProperty('app.user.password'))
System.setProperty('app.baseUrl', config.getProperty('app.baseUrl'))
System.setProperty('mongo.shell.path', config.getProperty('mongo.shell.path'))

// helper methods:

def loadConfigurations() {
	Properties properties = new Properties()
	properties.load(getClass().getResourceAsStream('resources/configuration.properties'))

	return properties
}

def loadBrowserDriver(String browser) {
    switch (browser) {
        case 'firefox':
            return getFirefoxDriver(null)
        case 'firefox-headless':
            return getFirefoxDriver(getHeadlessFirefoxOptions())
        case 'chrome':
            return getChromeDriver(null)
        case 'chrome-headless':
            return getChromeDriver(getHeadlessChromeOptions())
        default:
            return getChromeDriver(getHeadlessChromeOptions())
    }
}

def getFirefoxDriver(FirefoxOptions firefoxOptions) {
	if (isWindows()) {
		System.setProperty('webdriver.gecko.driver', config.getProperty('geb.webdriver.firefox.windows'))
	}

	if (isMacOs()) {
		System.setProperty('webdriver.gecko.driver', config.getProperty('geb.webdriver.firefox.macos'))
	}

	return firefoxOptions == null ? new FirefoxDriver() : new FirefoxDriver(firefoxOptions)
}

def getChromeDriver(ChromeOptions chromeOptions) {
	if (isWindows()) {
		System.setProperty('webdriver.chrome.driver', config.getProperty('geb.webdriver.chrome.windows'))
	}

	if (isMacOs()) {
		System.setProperty('webdriver.chrome.driver', config.getProperty('geb.webdriver.chrome.macos'))
	}

	return chromeOptions == null ? new ChromeDriver() : new ChromeDriver(chromeOptions)
}

def isWindows() {
	System.getProperty('os.name').toLowerCase().contains('windows')
}

def isMacOs() {
	System.getProperty('os.name').toLowerCase().contains('mac')
}

def getHeadlessFirefoxOptions() {
    def firefoxOptions = new FirefoxOptions()
    firefoxOptions.addArguments('--headless')

	return firefoxOptions
}

def getHeadlessChromeOptions() {
    def chromeOptions = new ChromeOptions()
    chromeOptions.addArguments("--headless")
    chromeOptions.addArguments("--disable-browser-side-navigation")
    chromeOptions.addArguments("--no-sandbox")

	return chromeOptions
}
