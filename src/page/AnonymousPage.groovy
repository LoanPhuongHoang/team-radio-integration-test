package page

class AnonymousPage extends GeneralPage {
	static url = '/station/anonymousstation'

	static at ={
		anonymousStationName.displayed
		anonymousStationName.text() == 'anonymousstation'
	}

	static content = {
		anonymousStationName{$('div.header-wrapper h1')}
		playList{$('div.flip-move-playlist div.item-container')}
		stationList{$('div.station-name span')}
	}
}
