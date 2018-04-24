package gebtest

import geb.spock.GebReportingSpec
import page.HelpPage

class HelpGebSpec extends GebReportingSpec{
    def'access Team Radio home page'(){
        when:
        to HelpPage

        then:
        userScoreRules.displayed
        userScoreRules.text() == 'User score rules:'
    }
}
