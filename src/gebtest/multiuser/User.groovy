package gebtest.multiuser

import geb.Browser
import geb.Page

interface User {
	String username
	String password
	Browser browser

	def login()
	def logout()
	def clickFavoriteIconAtSong(int songIndex)
	def clickThumbsdownIconAtSong(int songIndex)
	def clickThumbsdownIconAtSong(String songIndex)
	def clickThumbsupIconAtSong(int songIndex)
	def clickThumbsupIconAtSong(String songIndex)
	def searchSong(String songName)
	def addMessage(String message)
	def addSong(Map songWithMessage)
	def goTo(def page)
	def scrollDownToBottom()
	def scrollDownToMiddle()
	def openChatBox()
	def addMessageInChatBox(String message)
	def seeMessageOf(User user, String message)
	def closeChatBox()
	def seeChatButton()
}
