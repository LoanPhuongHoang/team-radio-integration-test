package gebtest

import geb.navigator.Navigator
import geb.spock.GebReportingSpec
import page.LoginPage
import page.StationTestPage
import page.TeamRadioHomePage
import page.UserProfilePage
import spock.lang.Ignore
import spock.lang.Stepwise


class FavoriteGebSpec extends GebReportingSpec{
//	@Stepwise
	def 'anonymous could not see any songs in favorite section'(){
		given:
		to StationTestPage

		when:
		def favoriteTab = allTabs.allElements().getAt(2)
		favoriteTab.click()

		then:
		noContentDisplay.displayed
		noContentDisplay.text() == 'You don\'t have any favourite songs' +
				'\nYou can add some songs you like from playlist tab.'
	}

	def 'user owner can see favorite songs in favorite tab on station page' (){
		given:
		to TeamRadioHomePage
		to LoginPage
		usernameInput << 'test'
		passwordInput << '123456'
		loginButton.click()

		and:
		at TeamRadioHomePage

		when:
		to StationTestPage
		def favoriteTab = allTabs.allElements().getAt(2)
		favoriteTab.click()

		def favoriteSongsInStation = collectFavoriteSongname(favoriteSongList)

		and:
		to UserProfilePage
		def favoriteLink = allLinks.allElements().getAt(1)
		favoriteLink.click()
		def favoriteSongsInProfile = collectFavoriteSongname(favoriteSongListInProfile)

		then:
		sleep(1000)
		favoriteSongsInStation == favoriteSongsInProfile

	}

	def collectFavoriteSongname (Navigator songs){
		songs.allElements().collect {song ->
		song.getText()
		}
	}
}
