package page

import geb.Page

class TeamRadioHomePage extends GeneralPage {
    static url = '/'

    static at = {
        title == 'Team Radio - A playlist for teams that can be edited collaboratively by all users'
		primaryHeading.displayed
    }

    static content = {
        primaryHeading {$('span.heading-primary--main')}
        createStationInput {$('input.transparent-form-control[name=name]')}
        createStationButton{$('div.input-wrapper button.button-submit')}
		warningStationMessage{$('div.text-error')}
        stationList{$('#station-browser')}
        stationTestThumbnail{$('#station-5adc0b3b47737f00046d4f29')}
    }
}
