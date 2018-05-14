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
	def clickThumbsupIconAtSong(int songIndex)
	def searchSong(String songName)
	def addMessage(String message)
	def addSong()
	def goTo(def page)
	def scrollDownToBottom()
	def scrollDownToMiddle()
}
