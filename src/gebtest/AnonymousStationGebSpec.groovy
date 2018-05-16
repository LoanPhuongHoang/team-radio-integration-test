//package gebtest
//
//import geb.spock.GebReportingSpec
//import gebtest.multiuser.MultiUserGebSpec
//import page.TeamRadioHomePage
//import page.AnonymousPage
//import spock.lang.Ignore
//
//class AnonymousStationGebSpec extends MultiUserGebSpec {
//
//	def 'anonymous with new station'(){
//		given:
//		def (stationOwner, user1, user2, anonUser1, anonUser2) = users
//
//		and:'Anonymous creates the own station and add 2 songs to that stations'
//		def songWithMessage = ['i want it that way': 'my favorite song',
//							   'shape of my heart':'super']
//
//		to TeamRadioHomePage
//
//		when:
//		guest1.scrollDownToMiddle()
//		guest1.createAnonymousStation()
//		guest1.addSong(songWithMessage)
//
//		then:
//		at AnonymousPage
//		playList.size() == 2
//
//		when:'guest2 join the anonymous station but could not add songs'
//		guest2.goTo AnonymousPage
//		guest2.addSong(songWithMessage)
//
//		then:
//
//
//		when: 'user1 join the anonymous station and adds 3 songs'
//		user1.login()
//		user1.goTo AnonymousPage
//		user1.addSong(songWithMessage)
//
//		then:
//		playList.size() == 2
//
//		when: 'guest1 registers and logging in'
//
//}
//
//
