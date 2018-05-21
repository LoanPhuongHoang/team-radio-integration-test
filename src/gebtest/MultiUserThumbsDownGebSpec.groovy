package gebtest

import gebtest.multiuser.MultiUserGebSpec
import page.StationTestPage
import spock.lang.IgnoreIf

class MultiUserThumbsDownGebSpec extends MultiUserGebSpec{

	@IgnoreIf({notLocalhost})
	def 'multiple users thumbs down test' (){
		given:
		def stationOwner = users['test']
		def user1 = users['testuser1']
		def user2 = users['testuser2']

		and: 'StationOwner logs in, goes to station test page and adds 3 songs'
		def songWithMessage = ['my love':'I like this song',
							   'uptown girl':'So cool',
							   'you raise me up':'It is so encouraged!']
		stationOwner.login()
		relax()
		to StationTestPage
		stationOwner.scrollDownToBottom()
		stationOwner.addSong(songWithMessage)

		def songIdList = getAllSongIdsInPlaylist()

		and:
		user1.login()
		relax()
		user1.goTo StationTestPage

		user2.login()
		relax()
		user2.goTo StationTestPage
		relax()

		when:
		user1.scrollTo(500)
		user1.clickThumbsdownIconAtSong(songIdList[1])
		relax()

		then:
		getSongIndexById(songIdList[1]) ==2

		when:
		user2.scrollTo(500)
		user2.clickThumbsdownIconAtSong(songIdList[2])
		relax()

		then:
		getSongIndexById(songIdList[2]) == 2

		when:
		relax()
		user2.scrollTo(500)
		user2.clickThumbsdownIconAtSong(songIdList[1])
		relax()

		then:
		getSongIndexById(songIdList[1]) == 2

		when:
		relax()
		user1.scrollTo(500)
		user1.clickThumbsdownIconAtSong(songIdList[2])
		relax()

		then:
		getSongIndexById(songIdList[2]) == 2
	}
}
