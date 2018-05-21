package gebtest

import geb.spock.GebReportingSpec
import page.TeamRadioHomePage

class HomePageAccessGebSpec extends GebReportingSpec {
    def 'access Team Radio home page'(){
        when:
        //go 'http://teamradio-dev.herokuapp.com/'
        to TeamRadioHomePage

        then:
//        at TeamRadioHomePage
        loginLinkHeader.displayed
        registerLinkHeader.displayed
        logoLinkHeader.displayed
        primaryHeading.displayed
        createStationInput.displayed

    }

//	def 'create private station'(){
//		when:
//		to TeamRadioHomePage
//
//		then:
//		privateStationButton.displayed
//	}

}
