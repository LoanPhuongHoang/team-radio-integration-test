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
	protected Map<String,User> users = createUserMap()
	protected notLocalhost = isNotLocalhost()

	private createUserMap() {
		def allUsernames = System.getProperty('app.user.all').split(',')
		def password = System.getProperty('app.user.password')

		def users = allUsernames.collectEntries { username ->
			def browser = new Browser()
			browser.config.cacheDriver = false
			def teamRadioUser = new TeamRadioUser(username, password, browser)
			def user = (User) Proxy.newProxyInstance(MultiUserGebSpec.classLoader,
													[User.class] as Class[],
													new UserInvocationHandler(teamRadioUser))

			[username, user]
		}

		users.putAll createAnonymousUserMap()
		users
	}

	private createAnonymousUserMap(){
		def anonymousUserMap = (0..1).collectEntries {
			def browser = new Browser ()
			browser.config.cacheDriver = false
			def anonymousUser = new TeamRadioUser(null, null, browser)
			def user = (User) Proxy.newProxyInstance(MultiUserGebSpec.classLoader,
													[User.class] as Class[],
													new UserInvocationHandler(anonymousUser))
			["anonUser${it+1}", user]
		}

		anonymousUserMap
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
		true
	}

	private void setActiveBrowser(Browser browser) {
		this.activeBrowser = browser
	}

	def getSongIndexById(String songId){
		int songIndex
		playList.allElements().eachWithIndex{song, index ->
			if (song.findElement(By.cssSelector('h6.item-title')).getAttribute('id') == songId){
				songIndex = index
			}
		}

		songIndex
	}

	def getAllSongIdsInPlaylist(){
		playList.allElements().collect {song ->
			song.findElement(By.cssSelector('h6.item-title')).getAttribute('id')
		}
	}

	def getMessageByUsername(String username){
		String message
			messageContainer.allElements().each {container ->
			if (container.findElement(By.cssSelector('div.username')).getText() == username){
				message = container.findElement(By.cssSelector('div.message-content')).getText()
			}
		}
		return message
	}

	def generateUniqueMessage(){
		'message at ' + System.currentTimeMillis()
	}

	def generateUniqueStationName(){
		'station ' + System.currentTimeMillis()
	}

	def isNotLocalhost(){
		!System.getProperty('app.baseUrl').contains('localhost')
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
			int songIndex

			playList.allElements().eachWithIndex{song, index ->
				if (song.findElement(By.cssSelector('h6.item-title')).getAttribute('id') == songId){
					songIndex = index
				}
			}

			clickThumbsdownIconAtSong(songIndex)
		}

		def clickThumbsdownIconAtSong(int songIndex){
			def thumbsdownIcon = playList.allElements().getAt(songIndex).findElement(By.cssSelector('i.fa-thumbs-down'))
			thumbsdownIcon.click()
		}


		def clickThumbsupIconAtSong(String songId) {
			int songIndex

			playList.allElements().eachWithIndex {song, index ->
				if (song.findElement(By.cssSelector('h6.item-title')).getAttribute('id')	== songId){
					songIndex = index
				}
			}

			clickThumbsupIconAtSong(songIndex)
		}

		def clickThumbsupIconAtSong(int songIndex){
			def thumbsupIcon = playList.allElements().getAt(songIndex).findElement(By.cssSelector('i.fa-thumbs-up'))
			thumbsupIcon.click()
		}

		def addSong(Map songWithMessage){
			songWithMessage.each{songName, message ->
				searchSong songName
				relax()
				addMessage message
				relax()
				clickAddSongButton()
				relax()
			}
		}

		def searchSong(String songName){
			waitFor{findSongBox.displayed}
			findSongBox << songName
			waitFor{searchResultBox.displayed}
			waitFor{firstResult.click()}
		}

		def addMessage(String message){
			messageInput << message
		}

		def clickAddSongButton(){
			addButton.click()
		}

		def goTo(def page){
			to page
		}

		def goTo(String url){
			go url
		}

		def scrollDownToBottom(){
			driver.executeScript("window.scrollTo(0, document.body.scrollHeight)")
		}

		def scrollTo(int position){
			driver.executeScript("window.scrollTo(0,$position)")
		}

		def addMessageInChatBox(String message){
			messageBoxChat << message
			messageEnterButton.click()
		}

		def seeMessageOf(User user, String message){
			getMessageByUsername(user.h.user.username) == message
		}

		def openChatBox(){
			chatBoxButton.click()
		}

		def closeChatBox(){
			minimumBoxChatButton.click()
		}

		def seeChatButton(){
			chatBoxButton.displayed
		}

		def createStation(String stationName){
			createStationInput << stationName
			createStationButton.click()
		}


//		private void getSize() throws Exception {
//			//Locate element for which you wants to get height and width.
//			WebElement Image = driver.findElement(By.xpath("//img[@border='0']"));
//
//			//Get width of element.
//			int ImageWidth = Image.getSize().getWidth();
//			System.out.println("Image width Is "+ImageWidth+" pixels");
//
//			//Get height of element.
//			int ImageHeight = Image.getSize().getHeight();
//			System.out.println("Image height Is "+ImageHeight+" pixels");
//		}

		private void takeControl() {
			setActiveBrowser(browser)
		}
	}
}
