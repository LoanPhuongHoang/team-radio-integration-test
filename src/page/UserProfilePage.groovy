package page

class UserProfilePage extends GeneralPage {
    static url = '/profile'

    static at = {
        title == 'Team Radio - A playlist for teams that can be edited collaboratively by all users'
        userDisplayName.displayed
        userDisplayName.text() =='test'
    }

    static content ={
        userDisplayName{$('div.name h3.display-name')}
        userAvatarButton{$("div.user-avatar img[alt='User Avatar']")}
        updateCoverPhotoButton{$('div.update-cover button.btn-update-cover')}
        imageUploader{$("input[type='file']")}
        croppingEditor{$('div.modal-content')}
        croppingBox{$('span.cropper-face.cropper-move')}
        applyButton{$('div.cropper-modal-footer button.btn-primary')}
        cancelButton{$('div.cropper-modal-footer button.btn-secondary')}

        uploadSuccessfulMessage{$('div.notifications-wrapper div.notification-message')}
        closeMessageButton{$('div.notifications-wrapper span.notification-dismiss')}

    }
}
