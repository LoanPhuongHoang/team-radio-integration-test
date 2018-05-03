package gebtest.multiuser

import geb.Browser
import geb.spock.GebReportingSpec
import org.openqa.selenium.By
import page.LoginPage

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

		def song = playList[songIndex]

		def clickFavoriteIconAtSong(int songIndex){
			setCurrentBrowser()
//			def song = playList[songIndex]
			def favoriteIcon = song.findElement(By.cssSelector('div.action-icon i.fa'))
			favoriteIcon.click()
		}

		def clickThumbsdownIconAtSong(int songIndex){
			setCurrentBrowser()
//			def song = playList[songIndex]
			def thumbsdownIcon = song.findElement(By.cssSelector('i.fa-thumbs-down'))
			thumbsdownIcon.click()
		}

		def clickThumbsupIconAtSong(int songIndex){
			setCurrentBrowser()
//			def song = playList[songIndex]
			def thumbsupIcon = song.findElement(By.cssSelector('i.fa-thumbs-up'))
			thumbsupIcon.click()
		}

		def searchSongInStationPage(){
			setCurrentBrowser()
			findSongBox << ['my love','uptown girl', 'if i let you go', 'you raise me up']
			waitFor{searchResultBox.displayed}
			waitFor{firstResult.click()}
			waitFor{videoPreviewer.displayed}
			isPreviewSpeakerMuted(previewSpeaker)
		}

		def addMessageInStationPage(){
			setCurrentBrowser()
			messageInput << 'I like this song'
		}

		def addSongInStationPage(){
			addButton.click()
			videoPlayer.displayed
			videoInPlaylist.displayed
		}

		def clickOn(Closure element) {

		}

		def doAction(Closure action){

		}

		private void setCurrentBrowser() {
			setCurrentBrowser(browser)
		}
	}
}
