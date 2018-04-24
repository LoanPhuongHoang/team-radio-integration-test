import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions

System.setProperty('webdriver.gecko.driver', 'C:\\6_Driver\\geckodriver.exe')
System.setProperty('webdriver.chrome.driver', 'C:\\6_Driver\\chromedriver_win32\\chromedriver.exe')

//there are 4 options: firefox, firefox-headless, chrome, chrome-headless
def browser = 'chrome'

driver = { loadBrowserDriver(browser) }
reportsDir = 'out/geb-reports'
reportOnTestFailureOnly = false
baseUrl = 'http://teamradio-dev.herokuapp.com'

def loadBrowserDriver(browser) {
    switch (browser) {
        case 'firefox':
            new FirefoxDriver()
            break;
        case 'firefox-headless':
            new FirefoxDriver(getHeadlessFirefoxOptions())
            break;
        case 'chrome':
            new ChromeDriver()
            break;
        case 'chrome-headless':
            new ChromeDriver(getHeadlessChromeOptions())
            break;
        default:
            new ChromeDriver(getHeadlessChromeOptions())
            break;

    }
}

def getHeadlessFirefoxOptions() {
    def firefoxOptions = new FirefoxOptions()
    firefoxOptions.addArguments('--headless')

    return firefoxOptions
}

def getHeadlessChromeOptions() {
    ChromeOptions chromeOptions = new ChromeOptions()
    chromeOptions.addArguments("--headless")
    chromeOptions.addArguments("--disable-browser-side-navigation")
    chromeOptions.addArguments("--no-sandbox")
    return chromeOptions
}

