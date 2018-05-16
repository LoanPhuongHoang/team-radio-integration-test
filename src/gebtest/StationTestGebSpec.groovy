package gebtest

import geb.spock.GebReportingSpec
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import page.LoginPage
import page.StationTestPage
import page.TeamRadioHomePage
import spock.lang.Ignore
import spock.lang.Stepwise

@Stepwise
class StationTestGebSpec extends GebReportingSpec{

    def 'access Station page'(){
        given:
		to LoginPage
		usernameInput << 'test'
		passwordInput << '123456'
		loginButton.click()

		and:
		at TeamRadioHomePage

		when:to StationTestPage


        then:
//        stationName.displayed
//        stationName.text() == 'stationtest1'

//        and:'other elements'
		waitFor { videoPlayer.displayed}
        hasPlayerSpeakerTurnedOn(playSpeaker)
        modeNormalButton.displayed
        shareStationButton.displayed
//        waitFor(10) {stationSettingButton.displayed}

        and:'check if theater mode work properly'
        modeNormalButton.displayed
        modeNormalButton.click()
        waitFor{modeTheaterButton.displayed}
        modeTheaterButton.click()
        modeNormalButton.displayed

    }

    @Ignore
    def 'access playlist'(){
        when:
        to StationTestPage

        then:
//        waitFor {stationName.present}
        def x
        if (playList.size() > 0){
            println playList.children()
        }
    }

	def 'check if playList is displayed correctly'() {
		when:
		at StationTestPage

		then:
		if (playList.size() > 0) {
			playList.allElements().each { song ->
				println song.findElement(By.cssSelector('h6.item-title')).getText()
				song.findElement(By.cssSelector('i.fa-thumbs-up')).displayed
				song.findElement(By.cssSelector('i.fa-thumbs-down')).displayed

				//check if favorite icon is displayed
				song.findElement(By.cssSelector('div.action-icon i.fa')).displayed
			}
		}
	}

	def 'favorite songs should be avaiable in favorite list'(){
		when:
		at StationTestPage

		then:
		if(playList.size()>0){
			def song = playList.allElements()[2]
			def favoriteIcon = song.findElement(By.cssSelector('div.action-icon i.fa'))
			favoriteIcon.click()
			def songId = song.findElement(By.cssSelector('h6.item-title')).getAttribute('id')

			def playListTab = allTabs.allElements().getAt(0)
			def favoriteTab = allTabs.allElements().getAt(2)
			favoriteTab.click()
			contextSongList.allElements().last().findElement(By.cssSelector('h6.item-title')).getAttribute('id') == songId
		}
	}

	def 'check if thumbsdown makes change for songs oder'(){
		when:
		at StationTestPage
		def playListTab = allTabs.allElements().getAt(0)
		playListTab.click()

		then: 'click downvote button on one specific song and get its position'
		def downvotedSongId

		if (playList.size() >0){
			playList.allElements().eachWithIndex { song, index ->
				if (index == 1) {
					downvotedSongId = song.findElement(By.cssSelector('h6.item-title')).getAttribute('id')
					song.findElement(By.cssSelector('i.fa-thumbs-down')).displayed
					song.findElement(By.cssSelector('i.fa-thumbs-down')).click()
					waitFor{song.findElement(By.cssSelector('span.dislike-icon')).getText() == '1'}
				}
			}
		}

		and: 'Check if downvoted song go to the last position'
//		def playListTab = allTabs.allElements().getAt(0)
//		def historyTab = allTabs.allElements().getAt(1)
//
//		historyTab.click()
//		playListTab.click()

		sleep(2000)

		playList.allElements().last().findElement(By.cssSelector('h6.item-title')).getAttribute('id') == downvotedSongId
	}


	def hasPlayerSpeakerTurnedOn(def playSpeaker){
		def classNames = playSpeaker.classes()

		if (classNames.contains('fa-volume-up'))
			return true
		if (classNames.contains('fa-volume-off'))
			return false
	}
}
//Upvote button
//song.findElement(By.cssSelector('i.fa-thumbs-up')).displayed
