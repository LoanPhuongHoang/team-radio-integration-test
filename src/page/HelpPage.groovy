package page

class HelpPage extends GeneralPage {
    static url = '/help'

    static at ={
        title == 'Team Radio - A playlist for teams that can be edited collaboratively by all users'
        userScoreRules.displayed
        userScoreRules.text() == 'User score rules:'
    }

    static content={
        userScoreRules{$('div.help-container h1')}
    }
}
