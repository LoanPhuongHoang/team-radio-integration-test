package gebtest

import geb.spock.GebReportingSpec
import page.StationTestPage
import page.TeamRadioHomePage

class StationTestGebSpec extends GebReportingSpec{
    def 'access Station page'(){
        when:
        to StationTestPage

        then:
        stationName.displayed
//        stationName.text() == 'stationtest1'

//        and:'other elements'
//        isPlayerSpeakerTurnOn(playSpeaker)
//        modeTheaterButtoon.displayed
//        shareStationButton.displayed
//        settingStationModeButton.displayed
    }

//    def isPlaySpeakerTurnOn(def playSpeaker){
//        def classNamesOne = playSpeaker.classes()
//
//        if (classNamesOne.contains('fa-volume-up '))
//            return true
//        if (classNamesOne.contains('fa-volume-off'))
//            return false
//    }


}
