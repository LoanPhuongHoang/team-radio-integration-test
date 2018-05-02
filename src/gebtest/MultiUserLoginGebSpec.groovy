package gebtest

import gebtest.multiuser.MultiUserGebSpec
import page.LoginPage
import page.TeamRadioHomePage

class MultiUserLoginGebSpec extends MultiUserGebSpec {

	def 'multiple users logging in'() {
		when:
		to TeamRadioHomePage
		users[0].login()
		users[1].login()
		relax()
		users[0].logout()
		users[1].logout()

		then:
		println users.size()
	}
}
