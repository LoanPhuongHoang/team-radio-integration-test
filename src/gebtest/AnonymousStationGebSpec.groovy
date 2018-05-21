//package gebtest
//
//import geb.spock.GebReportingSpec
//import gebtest.multiuser.MultiUserGebSpec
//import page.TeamRadioHomePage
//import page.AnonymousPage
//import spock.lang.Ignore
//
//class AnonymousStationGebSpec extends MultiUserGebSpec {
//	def stationOwner = users[0]
//	def user1 = users[1]
//	def user2 = users[2]
//	def anonUser1 = users[3]
//	def anonUser2 = users[4]
//
//	def 'anonymous with new station'(){
//		given:
//		def songWithMessage = ['i want it that way': 'my favorite song',
//							   'shape of my heart':'super']
//
//		to TeamRadioHomePage
//
//		when: 'Anonn User1 creates the own station and add 2 songs to that stations'
//		anonUser1.scrollTo(500)
//		anonUser1.createStation 'anonymousstation'
//		anonUser1.addSong(songWithMessage)
//
//		then:
//		at AnonymousPage
//		playList.size() == 2
//
//		when:'Anon User2 join the anonymous station but could not add songs'
//		anonUser2.goTo AnonymousPage
//		anonUser2.addSong(songWithMessage)
//
//		then:
//
//
//		when: 'user1 join the anonymous station and adds 2 songs'
//		user1.login()
//		user1.goTo AnonymousPage
//		user1.addSong(songWithMessage)
//
//		then:
//		playList.size() == 4
//
//		when: 'Anon User2 registers, login in and add 2 songs'
//
//
//}
//
//
