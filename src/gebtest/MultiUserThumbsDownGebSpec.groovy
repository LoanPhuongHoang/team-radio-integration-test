package gebtest

import gebtest.multiuser.MultiUserGebSpec
import org.openqa.selenium.By
import page.StationTestPage
import page.LoginPage
import page.TeamRadioHomePage

class MultiUserThumbsDownGebSpec extends MultiUserGebSpec{

	def 'multiple users thumbs down test' (){
		given:
		def (stationOwner, user1, user2) = users

		and: 'StationOwner logs in, goes to station test page and adds 3 songs'
		def songWithMessage = ['my love':'I like this song',
							   'uptown girl':'So cool',
							   'you raise me up':'It is so encouraged!']
		stationOwner.login()
		relax()
		to StationTestPage
		stationOwner.scrollDownToBottom()

		songWithMessage.each{songName, message ->
			stationOwner.searchSong songName
			relax()
			stationOwner.addMessage message
			relax()
			stationOwner.addSong()
			relax()
		}

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
		user1.scrollDownToMiddle()
		user1.clickThumbsdownIconAtSong(songIdList[1])
		relax()

		then:
		getSongIndexById(songIdList[1]) ==2

		when:
		user2.scrollDownToMiddle()
		user2.clickThumbsdownIconAtSong(songIdList[2])
		relax()

		then:
		getSongIndexById(songIdList[2]) == 2

		when:
		relax()
		user2.scrollDownToMiddle()
		user2.clickThumbsdownIconAtSong(songIdList[1])
		relax()

		then:
		getSongIndexById(songIdList[1]) == 2

		when:
		relax()
		user1.scrollDownToMiddle()
		user1.clickThumbsdownIconAtSong(songIdList[2])
		relax()

		then:
		getSongIndexById(songIdList[2]) == 2
	}

	def getAllSongIdsInPlaylist(){
		playList.allElements().collect {song ->
			song.findElement(By.cssSelector('h6.item-title')).getAttribute('id')
		}
	}

	def getSongIndexById(String songId){
		int songIndex
		playList.allElements().eachWithIndex{song, index ->
			if (song.findElement(By.cssSelector('h6.item-title')).getAttribute('id') == songId){
				songIndex = index
			}
		}

		songIndex
	}

}
