package page

class StationTestPage extends GeneralPage{
    static url = '/station/stationtest1'

    static at = {
        stationName.present
//        stationName.text() == 'stationtest1'
    }

    static content ={
        stationName{$('div.header-container h1')}
        addSong{$('div.add-song-container h1')}
        findSongBox{$("input[placeholder='Type a video name, e.g., Perfect - Ed Sheeran']")}
        searchResultBox{$('ul[role=listbox]')}
        firstResult{$('#react-autowhatever-1--item-0')}
        videoPreviewer{$("div.preview iframe")}
        previewSpeaker{$('div.preview__buttons i')}
        messageInput{$("input[placeholder='Do you want to say something about this video?']")}
        addButton{$('div.preview__buttons button.preview__button')}

        videoPlayer{$("div.player iframe")}
        videoInPlaylist{$('div.tab-pane.active img.video-img')}

        playSpeaker{$('div.buttons-wrapper div.station-mute-button i.icon')}

        modeNormalButton{$('div.player-container i.fa-lightbulb-o')}
        modeTheaterButton{$('div.player-container i.fa-lightbulb-o.activeButton ')}

        shareStationButton{$('#share-station i.fa-share-alt')}

        stationSettingButton{$('div.btn-icon i.fa-cog')}

        playList{$('div.flip-move-playlist div.item-container')}

		//for both history and favorite list
		contextSongList{$('div.tab-pane.active div.item-container')}
		allTabs{$('ul.nav.nav-tabs a.nav-link')}
		noContentDisplay{$('div.tab-pane+div.active div.playlist-none')}
		favoriteSongList{$('div.tab-pane+div.active h6.item-title')}

		//chat section
		chatBoxButton{$('div.chat-box-button')}
		messageBoxChat{$('#message-box')}
		messageEnterButton{$('i.fa-paper-plane')}
//		messageContent{username -> $("div.message-container div.display-none[text='$username'] + div.message-content")}
		messageContainer{$'div.message-left div.message-container'}
		minimumBoxChatButton{$('i.fa-minus')}
    }
}
