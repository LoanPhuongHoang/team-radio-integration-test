package gebtest

import geb.spock.GebReportingSpec
import org.openqa.selenium.By
import page.LoginPage
import page.RegisterPage
import page.TeamRadioHomePage
import page.UserProfilePage
import spock.lang.Ignore
import spock.lang.Shared
import spock.lang.Stepwise

@Stepwise
class UserProfileGebSpec extends GebReportingSpec {
	@Shared
	def userInfo = createUniqueUserInfo()
    //TODO: need to check that a user cannot access profile page without logging in
	def 'create new user'(){
		given:
		to RegisterPage

		when:
		displayNameInput << userInfo.username
		usernameInput << userInfo.username
		emailInput <<  "${userInfo.username}@gmail.com"
		passwordInput <<  userInfo.password
		confirmPasswordInput <<  userInfo.password
		signUpButton.click()

		then:
		at LoginPage
	}

    def 'Open profile page from home page after logging in'() {
        given: 'logging in'
        usernameInput << userInfo.username
        passwordInput << userInfo.password
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
        imageUploader << 'C:\\mgmwork\\team-radio-integration-test\\src\\resources\\images\\avatar\\avatar-test.png'

        then: 'display cropping editor'
        waitFor { croppingEditor.displayed }

        and: 'upload successfully'
        applyButton.click()
        waitFor(40) { uploadSuccessfulMessage.displayed }
        uploadSuccessfulMessage.text() == 'Your avatar has been successfully updated!'
        waitFor {closeMessageButton.displayed}
        closeMessageButton.click()

		and:'reputation should be increased 20'
		reputationScore.text().replace('Reputation: ','') == '20'

    }

    def 'Upload cover photo'() {
        when: 'upload image'
        updateCoverPhotoButton.click()
        imageUploader << 'C:\\mgmwork\\team-radio-integration-test\\src\\resources\\images\\cover\\6f3.png'

        then: 'display cropping editor'
        waitFor { croppingEditor.displayed }

        and: 'upload successfully'
        applyButton.click()
		sleep(4000)
//        waitFor(10) { uploadSuccessfulMessage.displayed }
//        uploadSuccessfulMessage.text() == 'Your cover photo has been successfully updated!'

		and: 'reputation should be increased 2'
		reputationScore.text().replace('Reputation: ', '') == '22'

    }

	@Ignore
	def 'Check if my station list is correct'(){
		given:
		def expectedMyStationList = ['asdf', 'abcd', 'khang', 'khang1', 'khang2', 'stationtest1']

		when:
		def myStationListResult = []
		myStationList.allElements().size() > 0
		myStationList.allElements().each { station ->
			myStationListResult << station.findElement(By.cssSelector('div.station-name span')).getText()
		}

		then:
		expectedMyStationList == myStationListResult
	}

//	TODO: need to be implemented
//	def 'Update user information'(){
//		when:
//		editProfileButton.click()
//		editInformationProfileSelection.click()
//		editInformationPopup.displayed
//		def newDisplayName = "test $System.currentTimeMillis()"
//		displayNameInput << newDisplayName
//		def newFirstName = "firstname $System.currentTimeMillis()"
//		firstNameInput << newFirstName
//		saveButton.click
//
//		then:
//	}

	def createUniqueUserInfo(){
		[username: "user${System.currentTimeMillis()}".substring(0,13), password: '123456']
	}
}