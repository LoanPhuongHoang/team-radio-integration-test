package gebtest

import geb.spock.GebReportingSpec
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import page.LoginPage
import page.StationTestPage
import page.TeamRadioHomePage

class HappyPathGebSpec extends GebReportingSpec{
    def 'Check main flow run successfully'(){
        given:
        to TeamRadioHomePage

        when: 'log in successfully'
        loginLinkHeader.click()
        at LoginPage
        usernameInput << 'test'
        passwordInput << '123456'
        loginButton.click()

        then: 'Display existing station successfully'
        at TeamRadioHomePage
        waitFor {stationList.displayed}
        scrollHorizontallyToStationTestThumbnail('station-item-container')
        stationTestThumbnail.displayed

        and: 'Entering specific station'
        stationTestThumbnail.click()
        scrollToTop()
        waitFor {at StationTestPage}

        and: 'search song'
        findSongBox << 'my love'
        waitFor{searchResultBox.displayed}
        waitFor{firstResult.click()}
        waitFor{videoPreviewer.displayed}
        isPreviewSpeakerMuted(previewSpeaker)

        and: 'add message'
        messageInput << 'I like this song'

        and: 'add song'
        addButton.click()
        videoPlayer.displayed
        videoInPlaylist.displayed
    }


    def scrollHorizontallyToStationTestThumbnail(String id) {
        WebElement element = driver.findElement(By.id(id))
        driver.executeScript("arguments[0].setAttribute('style', 'left: -15292px')", element)

        return element
    }

    def scrollToTop() {
        driver.executeScript("window.scrollTo(0,0)")

        return true
    }

    def isPreviewSpeakerMuted(def previewSpeaker) {
        def classNames = previewSpeaker.classes()

        if(classNames.contains('icon-volume-off'))
            return true
        if(classNames.contains('icon-volume-2'))
            return false
    }
}
