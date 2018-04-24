package page

import geb.Page

class RegisterPage extends GeneralPage {
    static url = '/register'

    static at = {
        title == 'Team Radio - A playlist for teams that can be edited collaboratively by all users'
        signupForm.displayed
        signupForm.text().contains('Sign Up')
    }

    static content = {
        signupForm {$('div.p-4')}

        displayNameInput{$('input[name=name]')}
        displayNameErrorMessage{$('input[name=name]+div.invalid-feedback')}

        usernameInput{$('input[name=username]')}
        usernameErrorMessage{$('input[name=username]+div.invalid-feedback')}

        emailInput{$('input[name=email]')}
        emailErrorMessage{$('input[name=email]+div.invalid-feedback')}

        passwordInput{$'input[name=password]'}
        passwordErrorMessage{$('input[name=password]+div.invalid-feedback')}

        confirmPasswordInput{$'input[name=confirmPassword'}
        confirmPasswordErrorMessage{$('input[name=confirmPassword+div.invalid-feedback')}

        signUpButton{$'div.mb-3 + button.btn-success'}
        havingAccountMessage{$'div.login-callout span'}
        loginLink{$("div.login-callout span a[href='/login']")}

    }
}
