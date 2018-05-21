package scripts

def mongoPath = System.getProperty('mongo.shell.path')
def filePath = 'resources/database/stationtest1.json'

"$mongoPath\\mongoimport --db localhost:27017/local_radio --collection station --file $filePath --jsonArray".execute()
