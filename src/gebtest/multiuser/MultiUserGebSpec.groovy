package gebtest.multiuser

import geb.Browser
import geb.spock.GebReportingSpec
import page.LoginPage

abstract class MultiUserGebSpec extends GebReportingSpec {

//	private Map<User, Browser> userBrowserMap = createUserList()
//	private Browser overridenBrowser
//	private Properties config = loadConfigurations()

	private Browser currentBrowser
	protected List<User> users = createUserList()

	private createUserList() {
//		Properties config = loadConfigurations()
		def allUsernames = System.getProperty('app.user.all').split(',')
		def password = System.getProperty('app.user.password')

		def users = allUsernames.collect { username ->
			def browser = new Browser()
			browser.config.cacheDriver = false
			def user = new User(username, password, browser)
			user
		}
	}

	@Override
	Browser getBrowser() {
		currentBrowser ?: super.browser
	}

	@Override
	void resetBrowser() {
		super.resetBrowser()

		users.each { user ->
			user.browser.clearCookiesQuietly()
			user.browser.close()
		}
	}

	private void setCurrentBrowser(Browser browser) {
		this.currentBrowser = browser
	}

	private class User {

		private String username
		private String password
		private Browser browser

		User(String username, String password, Browser browser) {
			this.username = username
			this.password = password
			this.browser = browser
		}

		def login() {
			setCurrentBrowser()
			to LoginPage
			usernameInput << username
			passwordInput << password
			loginButton.click()
		}

		def logout() {
			setCurrentBrowser()
			userInfoDropDownButton.click()
			signoutButtonHeader.click()
		}

		def clickOn(Closure element) {

		}

		private void setCurrentBrowser() {
			setCurrentBrowser(browser)
		}
	}
}
