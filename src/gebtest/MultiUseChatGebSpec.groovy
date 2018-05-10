package gebtest

import gebtest.multiuser.MultiUserGebSpec
import page.StationTestPage
import spock.lang.Shared

class MultiUseChatGebSpec extends MultiUserGebSpec {

	def user1 = users[1]
	def user2 = users[2]

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
		user2.scrollDownToBottom()

		then:

		user1.addMessageInChatBox 'hi'
		relax()
		user2.seeMessageOf user1
		relax()
		user2.addMessageInChatBox 'hello'
		relax()
		user1.seeMessageOf user2
		relax()
		user1.addMessageInChatBox 'so cool'
		relax()
		user2.seeMessageOf user1
		relax()
		user2.addMessageInChatBox 'agree'
		relax()
		user1.seeMessageOf user2
	}
}
