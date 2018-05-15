package gebtest

import gebtest.multiuser.MultiUserGebSpec
import org.openqa.selenium.By
import page.LoginPage
import page.StationTestPage
import page.TeamRadioHomePage

class MultiUserThumbsUpGebSpec extends MultiUserGebSpec {

	def 'multiple users thumbs up test'() {
		given:
		def (stationOwner, user1, user2) = users

		and:'StationOwner logs in, goes to station test page and adds 3 songs'
		def songWithMessage = ['my love':'I like this song',
							   'uptown girl':'So cool',
							   'you raise me up':'It is so encouraged!']
		stationOwner.login()
		relax()
		to StationTestPage
		stationOwner.scrollDownToBottom()
		stationOwner.addSong(songWithMessage)

		def songIdList = getAllSongIdsInPlaylist()

		and: 'user1 and user2 log in and go to station test page'
		user1.login()
		relax()
		user1.goTo StationTestPage

		user2.login()
		relax()
		user2.goTo StationTestPage
		relax()

		when: 'user1 clicks thumbs up on last song in current playlist'
		user1.scrollDownToMiddle()
		user1.clickThumbsupIconAtSong(songIdList[2])
		relax()

		then: 'new index of that song in the play list will be 1'
		getSongIndexById(songIdList[2]) == 1

		when:'user 2 clicks thumbs up on the last song in current playlist'
		user2.scrollDownToMiddle()
		user2.clickThumbsupIconAtSong(songIdList[1])
		relax()

		then:'new index of that song in the playlist will be 1'
		getSongIndexById(songIdList[1]) == 1

		when:'user 2 clicks thumbs up on the last song in current playlist'
		relax()
		user2.scrollDownToMiddle()
		user2.clickThumbsupIconAtSong(songIdList[2])
		relax()

		then:'new index of that song in the playlist will be 1'
		getSongIndexById(songIdList[2]) == 1

		when:'use 1 clicks thumbs up on the last song in current playlist'
		relax()
		user1.scrollDownToMiddle()
		user1.clickThumbsupIconAtSong(songIdList[1])
		relax()

		then:'new index of that song in the playlist will be 1'
		getSongIndexById(songIdList[1]) ==1
	}
}

