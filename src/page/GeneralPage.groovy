package page

import geb.Page

class GeneralPage extends Page{
    static content ={
        loginLinkHeader {$("a[href='/login']")}
        registerLinkHeader {$("a[href='/register']")}
        logoLinkHeader {$("div.logo a[href='/']")}
        userInfoDropDownButton {$('div.user-info div.drop-down button.dropdown-toggle')}

        yourProfileButtonHeader{$("a[href='/profile']")}
        signoutButtonHeader {$("a[href='/help']+button")}

    }

}
