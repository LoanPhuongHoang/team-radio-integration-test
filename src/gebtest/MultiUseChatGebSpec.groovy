package gebtest

import gebtest.multiuser.MultiUserGebSpec
import page.StationTestPage
import spock.lang.Shared

class MultiUseChatGebSpec extends MultiUserGebSpec {

	def user1 = users[1]
	def user2 = users[2]
	def uniqueMessage

	def 'users logging in'() {
		when: 'user 1 and user2 log in and go to station test page'
		user1.login()
		relax()
		user1.goTo StationTestPage

		user2.login()
		relax()
		user2.goTo StationTestPage
		relax()

		user1.scrollDownToBottom()
		user1.openChatBox()

		user2.scrollDownToBottom()
		user2.openChatBox()

		uniqueMessage = generateUniqueMessage()
		user1.addMessageInChatBox uniqueMessage
		relax()

		then:
		user2.seeMessageOf user1, uniqueMessage
		relax()

		when:
		uniqueMessage = generateUniqueMessage()
		user2.addMessageInChatBox uniqueMessage
		relax()

		then:
		user1.seeMessageOf user2, uniqueMessage
		relax()

		when:
		uniqueMessage = generateUniqueMessage()
		user1.addMessageInChatBox uniqueMessage
		relax()

		then:
		user2.seeMessageOf user1, uniqueMessage
		relax()

		and:
		user1.closeChatBox()
		user1.seeChatButton()

//		user1.minimumBoxChatButton.displayed
//		user1.minimumBoxChatButton.click()
//		user1.chatBoxButton.displayed

		user2.closeChatBox()
		user2.seeChatButton()

//		user2.minimumBoxChatButton.displayed
//		user2.minimumBoxChatButton.click()
//		user2.chatBoxButton.displayed

	}
}
