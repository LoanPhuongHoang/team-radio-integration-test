package gebtest.multiuser

import geb.Browser
import geb.spock.GebReportingSpec
import org.openqa.selenium.By
import page.LoginPage
import page.StationTestPage
import proxy.UserInvocationHandler

import java.lang.reflect.Proxy

abstract class MultiUserGebSpec extends GebReportingSpec {

	private Browser activeBrowser
	protected List<User> users = createUserList()

	private createUserList() {
		def allUsernames = System.getProperty('app.user.all').split(',')
		def password = System.getProperty('app.user.password')

		def users = allUsernames.collect { username ->
			def browser = new Browser()
			browser.config.cacheDriver = false
			def teamRadioUser = new TeamRadioUser(username, password, browser)
			def user = (User) Proxy.newProxyInstance(MultiUserGebSpec.classLoader,
													[User.class] as Class[],
													new UserInvocationHandler(teamRadioUser))
			user
		}
	}

	@Override
	Browser getBrowser() {
		activeBrowser ?: super.browser
	}

	@Override
	void resetBrowser() {
		super.resetBrowser()

		users.each { user ->
			def browser = Proxy.getInvocationHandler(user).getUser().browser
			browser.clearCookiesQuietly()
			browser.close()
		}
	}

	static protected relax(seconds = 2) {
		sleep seconds*1000
	}

	private void setActiveBrowser(Browser browser) {
		this.activeBrowser = browser
	}

	private class TeamRadioUser implements User {

		private String username
		private String password
		private Browser browser

		TeamRadioUser(String username, String password, Browser browser) {
			this.username = username
			this.password = password
			this.browser = browser
		}

		def login() {
			to LoginPage
			usernameInput << username
			passwordInput << password
			loginButton.click()
		}

		def logout() {
			userInfoDropDownButton.click()
			signoutButtonHeader.click()
		}

		def clickFavoriteIconAtSong(int songIndex){
			to StationTestPage
			def favoriteIcon = playList[songIndex].findElement(By.cssSelector('div.action-icon i.fa'))
			favoriteIcon.click()
		}


		def clickThumbsdownIconAtSong(String songId){
			setCurrentBrowser()
			int songIndex

			playList.allElements().eachWithIndex{song, index ->
				if (song.findElement(By.cssSelector('h6.item-title')).getAttribute('id') == songId){
					songIndex = index
				}
			}

			clickThumbsdownIconAtSong(songIndex)

//			def thumbsdownIcon = playList[songIndex].findElement(By.cssSelector('i.fa-thumbs-down'))
//			thumbsdownIcon.click()
		}

		def clickThumbsdownIconAtSong(int songIndex){
			setCurrentBrowser()
			def thumbsdownIcon = playList.allElements().getAt(songIndex).findElement(By.cssSelector('i.fa-thumbs-down'))
			thumbsdownIcon.click()
		}


		def clickThumbsupIconAtSong(String songId) {
			setCurrentBrowser()
			int songIndex

			playList.allElements().eachWithIndex {song, index ->
				if (song.findElement(By.cssSelector('h6.item-title')).getAttribute('id')	== songId){
					songIndex = index
				}
			}

			clickThumbsupIconAtSong(songIndex)
		}

		def clickThumbsupIconAtSong(int songIndex){
			setCurrentBrowser()
			def thumbsupIcon = playList.allElements().getAt(songIndex).findElement(By.cssSelector('i.fa-thumbs-up'))
			thumbsupIcon.click()
		}

		def searchSong(String songName){
			waitFor{findSongBox.displayed}
			findSongBox << songName
			waitFor{searchResultBox.displayed}
			waitFor{firstResult.click()}
		}

		def addMessage(String message){
			setCurrentBrowser()
			messageInput << message
		}

		def addSong(){
			addButton.click()
		}

		def goTo(def page){
			setCurrentBrowser()
			to page
		}

		def scrollDownToBottom(){
			driver.executeScript("window.scrollTo(0,500)")
		}

		def scrollDownToMiddle(){
			driver.executeScript("window.scrollTo(0,200)")
		}

		private void setCurrentBrowser() {
			setCurrentBrowser(browser)
		}

		private void takeControl() {
			setActiveBrowser(browser)
		}
	}
}
