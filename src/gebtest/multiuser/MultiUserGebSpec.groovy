package gebtest.multiuser

import geb.Browser
import geb.Page
import geb.spock.GebReportingSpec
import org.openqa.selenium.By
import page.LoginPage
import page.StationTestPage
import spock.lang.Ignore

abstract class MultiUserGebSpec extends GebReportingSpec {

	private Browser currentBrowser
	protected List<User> users = createUserList()

	private createUserList() {
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

	static protected relax(seconds = 2) {
		sleep seconds*1000
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

		def clickFavoriteIconAtSong(int songIndex){
			setCurrentBrowser()
			to StationTestPage
			def favoriteIcon = playList[songIndex].findElement(By.cssSelector('div.action-icon i.fa'))
			favoriteIcon.click()
		}
		@Ignore
		def clickThumbsdownIconAtSong(int songIndex){
			setCurrentBrowser()
			to StationTestPage
			def thumbsdownIcon = playList[songIndex].findElement(By.cssSelector('i.fa-thumbs-down'))
			thumbsdownIcon.click()
		}
		@Ignore
		def clickThumbsupIconAtSong(int songIndex){
			setCurrentBrowser()
			to StationTestPage
			def thumbsupIcon = playList[songIndex].findElement(By.cssSelector('i.fa-thumbs-up'))
			thumbsupIcon.click()
		}

		def searchSong(String songName){
			setCurrentBrowser()
			waitFor{findSongBox.displayed}
			findSongBox << songName
			waitFor{searchResultBox.displayed}
			waitFor{firstResult.click()}
			waitFor{videoPreviewer.displayed}
		}

		def addMessage(String message){
			setCurrentBrowser()
			messageInput.displayed
			messageInput << message
		}

		def addSong(){
			setCurrentBrowser()
			addButton.click()
		}

		def clickOn(Closure element) {

		}

		def doAction(Closure action){

		}

		def goTo(def page){
			setCurrentBrowser()
			to page
		}

		def scrollDown(){
			driver.executeScript("window.scrollTo(0,500)")
		}

		private void setCurrentBrowser() {
			setCurrentBrowser(browser)
		}
	}
}
