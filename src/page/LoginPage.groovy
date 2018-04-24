package page

import geb.Page

class LoginPage extends GeneralPage {
    static url = '/login'

    static at ={
        title == 'Team Radio - A playlist for teams that can be edited collaboratively by all users'
        loginForm.displayed
        loginForm.text() == 'Login'
    }

    static content = {
        loginForm {$('div.mx-4 div.p-4 h1')}
        usernameInput {$('input[name=username]')}
        userNameErrorMessage{$('input[name=username]+div.invalid-feedback')}

        passwordInput {$('input[name=password]')}
        passwordErrorMessage{$('input[name=password]+div.invalid-feedback')}

        loginButton {$('form button')}
        errorLoginMessage{$('form div.alert-danger')}

        createNewAccountLink{$("div.login__register-callout a[href='/register']")}

    }
}
