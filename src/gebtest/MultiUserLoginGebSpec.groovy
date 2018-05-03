package gebtest

import gebtest.multiuser.MultiUserGebSpec
import page.LoginPage
import page.StationTestPage
import page.TeamRadioHomePage

class MultiUserLoginGebSpec extends MultiUserGebSpec {

	def 'multiple users logging in'() {
		given:
		def (stationOwner, user1, user2) = users

		when:
		to TeamRadioHomePage
		user1.login()
		user2.login()
		relax()

		user1.goTo StationTestPage
		relax()
		user1.scrollDown()
		user1.searchSong 'my love'
		relax()
		user1.addMessage 'flfsjf'
		user1.addSong()


		user2.goTo StationTestPage
		relax()
		user2.scrollDown()
		user2.searchSong('as long as you love me')
		relax()
		user2.addMessage 'flfsjf'
		user2.addSong()

//		user1.goTo StationTestPage
//		relax()
//		user1.scrollDown()
//		user1.addMessage()

//		users[2].addMessage()
//
//		user[1].addSong()
//		user[2].addSong()

//		users[1].clickFavoriteIconAtSong 2

		then:
		println users.size()
	}
}
