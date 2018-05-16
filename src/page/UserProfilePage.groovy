package page

class UserProfilePage extends GeneralPage {
    static url = '/profile'

    static at = {
        title == 'Team Radio - A playlist for teams that can be edited collaboratively by all users'
        userDisplayName.displayed
    }

    static content ={
        userDisplayName{$('div.name h3.display-name')}
        userAvatarButton{$("div.user-avatar img")}
        updateCoverPhotoButton{$('div.update-cover button.btn-update-cover')}
        imageUploader{$("input[type='file']")}
        croppingEditor{$('div.modal-content')}
        croppingBox{$('span.cropper-face.cropper-move')}
        applyButton{$('div.cropper-modal-footer button.btn-primary')}
        cancelButton{$('div.cropper-modal-footer button.btn-secondary')}

        uploadSuccessfulMessage{$('div.notifications-wrapper div.notification-message')}
        closeMessageButton{$('div.notifications-wrapper span.notification-dismiss')}

		myStationList {$('div.station-item-container div.station-item')}

		editProfileButton {$('i.fa-pencil')}
		editInformationProfileSelection {$('div.dropdown-menu-right.show button.dropdown-item i.fa-user')}
		editInformationPopup {$('div.modal-content')}
		displayNameInput {$("input[placeholder='Enter your Display name']" )}
		firstNameInput {$("input[placeholder ='Enter your First name']")}
		saveButton {$('div.footer-form button.btn-primary')}

		allLinks {$('li.nav-item')}
		favoriteSongListInProfile{$('div.favorite-song-name')}

		reputationScore{$('span.reputation')}
    }
}
