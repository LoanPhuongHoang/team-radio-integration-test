package page

class StationTest2Page extends GeneralPage{
	static url = '/station/stationtest2'

	static at = {
		stationName.displayed
	}

	static content = {
		stationName { $('div.header-container h1') }
		stationList {$('div.station-name span')}
	}
}
