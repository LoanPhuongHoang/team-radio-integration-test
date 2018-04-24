package gebtest

import geb.spock.GebReportingSpec
import page.LoginPage
import page.RegisterPage
import page.TeamRadioHomePage
import spock.lang.Ignore

class LoginGebSpec extends GebReportingSpec {

    def 'Open Login page from home page'(){
        given:
        to TeamRadioHomePage

        when:
        loginLinkHeader.click()

        then:
        at LoginPage
    }

    def 'Login failed'(){
        given:
        to LoginPage

        when:
        usernameInput << username
        passwordInput << password
        loginButton.click()

        then:
        expectedResult

        where:
        username| password  |expectedResult
        ''      | ''        |{userNameErrorMessage.displayed && passwordErrorMessage.displayed && userNameErrorMessage.text() == 'Username or Email is required.' && passwordErrorMessage.text() == 'Password is required.'}
        'abc'   | ''        |{passwordErrorMessage.displayed && passwordErrorMessage.text() == 'Password is required.'}
        ''      | '123'     |{userNameErrorMessage.displayed && userNameErrorMessage.text() == 'Username or Email is required.'}
        'abc'   | '123'     |{errorLoginMessage.displayed && errorLoginMessage.text() == 'Bad credentials'}
    }

    def 'Login successfully with username and password'(){
        given:
        to LoginPage

        when:
        usernameInput << 'test'
        passwordInput << '123456'
        loginButton.click()

        then:
        at TeamRadioHomePage

        and:
        waitFor(10) {userInfoDropDownButton.displayed}
        userInfoDropDownButton.click()
        waitFor(10) {signoutButtonHeader.displayed}
        signoutButtonHeader.click()
    }

    def 'Go to register page from log in page'(){
        given:
        to LoginPage

        when:
        createNewAccountLink.click()

        then:
        at RegisterPage
    }
}
