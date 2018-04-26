package gebtest

import geb.spock.GebReportingSpec
import page.StationTestPage
import page.TeamRadioHomePage
import spock.lang.Ignore

class StationTestGebSpec extends GebReportingSpec{


    def 'access Station page'(){
        when:
        to StationTestPage

        then:
//        stationName.displayed
//        stationName.text() == 'stationtest1'

//        and:'other elements'
        isPlayerSpeakerTurnOn(playSpeaker)
        modeNormalButtoon.displayed
        shareStationButton.displayed
        settingStationModeButton.displayed

        and:'check if theater mode work properly'
        modeNormalButtoon.displayed
        modeNormalButtoon.click()
        modeTheaterButtoon.displayed
        modeTheaterButtoon.click()
        modeNormalButtoon.displayed

    }

    def isPlaySpeakerTurnOn(def playSpeaker){
        def classNamesOne = playSpeaker.classes()

        if (classNamesOne.contains('fa-volume-up '))
            return true
        if (classNamesOne.contains('fa-volume-off'))
            return false
    }

    def 'access playlist'(){
        when:
        to StationTestPage

        then:
//        waitFor {stationName.present}
        def x
        if (playList.size() > 0){
            println playList.children()
//            x = playList.size()
        }
//      playList.getAt(1).send


    }

}


// playList.allElements().getAt(0).findElement(org.openqa.selenium.By.cssSelector('h6.item-title')).getText()