package gebtest

import gebtest.multiuser.MultiUserGebSpec
import page.StationTest2Page
import page.StationTestPage
import page.TeamRadioHomePage
import scripts.ScriptRunner
import spock.lang.Ignore
import spock.lang.IgnoreIf
import spock.lang.Stepwise

@Stepwise
class StationListGebSpec extends MultiUserGebSpec {
	def stationOwner = users['test']
	def user1 = users['testuser1']
	def user2 = users['testuser2']
	def user3 = users['testuser3']
	def anonuser1 = users['anonuser1']

//	@IgnoreIf({notLocalhost})
//	def 'prepare database'() {
//		ScriptRunner.clearStationList()
//	}

//	@IgnoreIf({!System.getProperty('app.baseUrl').contains('localhost')})
	@Ignore
	def 'create new station'(){
		given:
		def stationName = generateUniqueStationName()

		when:
		stationOwner.login()
		stationOwner.goTo TeamRadioHomePage
		stationOwner.createStation stationName

		then:
		true
//		stationList.allElements()[0].getText() == stationName
	}

//	@IgnoreIf({notLocalhost})
//	@Ignore
	def '2 users join an existing station'(){
		when:
		user1.login()
		relax()
		user1.goTo StationTestPage
		relax()
		user2.login()
		relax()
		user2.goTo StationTestPage

		then: 'stationtest1 should be at second place in playlist and online number is 2'
		onlineUserNumber.text() == '2 online'
//		stationList.allElements()[1].getText() == 'stationtest1'
	}

//	@IgnoreIf({notLocalhost})
	@Ignore
	def '1 user and 1 anon user join an existing station'(){
		when:
		user3.login()
		user3.goTo StationTest2Page
		anonuser1.goTo StationTest2Page
		relax()

		then: 'stationtest2 should be at third place'
		onlineUserNumber.text() == '1 online'
		stationList.allElements()[2].getText() == 'stationtest2'
	}

}
