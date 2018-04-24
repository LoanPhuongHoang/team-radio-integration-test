package gebtest

import geb.spock.GebReportingSpec
import page.LoginPage
import page.TeamRadioHomePage

class SignOutGebSpec extends GebReportingSpec{
    def 'Logging in and logging out from home page'(){
        given: 'logging in'
        to TeamRadioHomePage
        loginLinkHeader.click()
        at LoginPage
        usernameInput << 'test'
        passwordInput << '123456'
        loginButton.click()
        when:
        waitFor { userInfoDropDownButton.displayed }
        userInfoDropDownButton.click()
        signoutButtonHeader.click()
        then:
        at TeamRadioHomePage
    }
}
