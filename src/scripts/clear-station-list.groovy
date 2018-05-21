package scripts

def mongoPath = System.getProperty('mongo.shell.path')

"$mongoPath\\mongo localhost:27017/local_radio --eval \"db.station.remove()\"".execute()

