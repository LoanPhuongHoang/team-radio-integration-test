package gebtest

import geb.spock.GebReportingSpec
import page.LoginPage
import page.TeamRadioHomePage
import page.UserProfilePage
import spock.lang.Ignore
import spock.lang.Stepwise

@Stepwise
class UserProfileGebSpec extends GebReportingSpec {

    //TODO: need to check that a user cannot access profile page without logging in

    def 'Open profile page from home page after logging in'() {
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
        yourProfileButtonHeader.click()

        then:
        at UserProfilePage
    }

    def 'Upload avatar picture'() {
        when: 'upload image'
        userAvatarButton.click()
        imageUploader << 'C:\\1_Working\\9_Automation test\\team-radio\\resources\\avatar\\avatar-test.png'

        then: 'display cropping editor'
        waitFor { croppingEditor.displayed }

        and: 'upload successfully'
        applyButton.click()
        waitFor(40) { uploadSuccessfulMessage.displayed }
        uploadSuccessfulMessage.text() == 'Your avatar has been successfully updated!'
        waitFor {closeMessageButton.displayed}
        closeMessageButton.click()
    }

    def 'Upload cover photo'() {
        when: 'upload image'
        updateCoverPhotoButton.click()
        imageUploader << 'C:\\1_Working\\9_Automation test\\team-radio\\resources\\cover\\6f3.png'

        then: 'display cropping editor'
        waitFor { croppingEditor.displayed }

        and: 'upload successfully'
        applyButton.click()
        waitFor(40) { uploadSuccessfulMessage.displayed }
        uploadSuccessfulMessage.text() == 'Your cover photo has been successfully updated!'

    }
}