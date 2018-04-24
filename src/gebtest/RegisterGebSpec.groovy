package gebtest

import geb.spock.GebReportingSpec
import page.LoginPage
import page.RegisterPage
import page.TeamRadioHomePage

class RegisterGebSpec extends GebReportingSpec{
    def 'Open Register page from home page'(){
        given:
        to TeamRadioHomePage

        when:
        registerLinkHeader.click()

        then:
        at RegisterPage
    }

    def'Check if display name is acceptable'(){
        given:
        to RegisterPage

        when:
        displayNameInput << displayName
//        signUp.click()

        then:
        expectedResult

        where:
        displayName         | expectedResult
        ''                  |{displayNameErrorMessage.displayed && displayNameErrorMessage.text() == 'Display name is required.'}
        '!@#'               |{displayNameErrorMessage.displayed && displayNameErrorMessage.text() == 'Display name may only contain alphanumeric characters.'}
        '1234567890123456'  |{displayNameErrorMessage.displayed && displayNameErrorMessage.text() == 'Display name must be less than 15 characters.'}
        'abc'               |{!displayNameErrorMessage.displayed}

    }

    def 'Check if username is acceptable'(){
        given:
        to RegisterPage

        when:
        usernameInput << username

        then:
        expectedResult

        where:
        username    |expectedResult
        '!@#'       |{usernameErrorMessage.displayed && usernameErrorMessage.text() == 'Username may only contain alphanumeric characters or single hyphens, and cannot begin or end with a hyphen.'}
        'abc'       |{!usernameErrorMessage.displayed}
        ''          |{!usernameErrorMessage.displayed}
    }

    def 'Check if email is acceptable'(){
        given:
        to RegisterPage

        when:
        emailInput << email

        then:
        expectedResult

        where:
        email               |expectedResult
        ''                  |{emailErrorMessage.displayed && emailErrorMessage.text() == 'Email is required.'}
        'a@gmail'           |{emailErrorMessage.displayed && emailErrorMessage.text() == 'Email must be a valid address.'}
        'test@gmail.com'    |{!emailErrorMessage.displayed}

    }
    def 'Check if password and confirm password is acceptable'(){
        given:
        to RegisterPage

        when:
        passwordInput << password
        confirmPasswordInput << confirmPassword

        then:
        expectedResult

        where:
        password    |confirmPassword    |expectedResult
        ''          |''                 |{passwordErrorMessage.displayed && passwordErrorMessage.text()== 'Password is required.'}
        '123'       |''                 |{passwordErrorMessage.displayed && passwordErrorMessage.text()== 'Password must be at least 6 characters.'}
        '123456'    |''                 |{confirmPasswordErrorMessage.displayed && confirmPasswordErrorMessage.text()== 'Confirm password is required.'}
        '123456'    |'123'              |{confirmPasswordErrorMessage.displayed && confirmPasswordErrorMessage.text()== 'Password does not match the confirm password.'}
        '123456'    |'123456'           |{!confirmPasswordErrorMessage.displayed}

    }

    def 'Register with displayName, username, email, password and confirm password' (){
        given:
        to RegisterPage

        when:
        displayNameInput        <<  'test1'
        usernameInput           <<  'test1'
        emailInput              <<  'test1@gmail.com'
        passwordInput           <<  '123456'
        confirmPasswordInput    <<  '123456'
        signUpButton.click()

        then:
        at TeamRadioHomePage
    }

    def 'Go to log in page from register page' (){
        given:
        to RegisterPage

        when:
        loginLink.click()

        then:
        at LoginPage

    }
    def 'go to Team Radio home page by clicking the logo from register page'(){
        given:
        to RegisterPage

        when:
        logoLinkHeader.click()

        then:
        at TeamRadioHomePage
    }

}
