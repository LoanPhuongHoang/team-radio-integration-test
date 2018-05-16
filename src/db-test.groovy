def mongoPath = 'C:\\software\\mongodb-3.6.4\\bin'

def collections = ['favorite_song', 'history', 'message',
				   'radio_oauth2_access_token', 'radio_oauth2_refresh_token', 'recent_station',
				   'reputation', 'reputation_event_log', 'song',
				   'station', 'user']

def createCollectionsQuery = ''
collections.each {collection ->
	createCollectionsQuery += "db.createCollection('$collection');"
}

def removeAllDataQuery = ''
collections.each {collection ->
	removeAllDataQuery += 'db.' + collection + '.remove({});'
}

def userData_test = "{_id: 'ObjectId(\"5adad567356be4000446af76\")'," +
					"username: 'test'," +
					"password: '\$2a\$08\$0LmASQkhyb69ll1JTq4fsuwcsNXhqpXujzyQ1rFFk6yckebtPDZ76'," +
					"roles: [{authority : 'USER'}]," +
					"enabled: 'true'," +
					"reputation: 0," +
					"_class: 'com.mgmtp.radio.domain.user.User'}"

def userData_testuser1 = "{_id: 'ObjectId(\"5ae59cf2c71f87000451230d\")'," +
						"username: 'testuser1'," +
						"password: '\$2a\$08\$wChbwCM.uA4okeQTdqzD5OKnWKLduTsT4CDeWBqArXv6qmFAvH0Mq'," +
						"roles: [{authority : 'USER'}]," +
						"enabled: true," +
						"reputation: 0," +
						"_class: 'com.mgmtp.radio.domain.user.User'}"

def userData_testuser2 = "{_id: 'ObjectId(\"5ae59d61c71f87000451230f\")'," +
						"username: 'testuser2'," +
						"password: '\$2a\$08\$d5LnP9o3ejqsfh18WO1Gt.hiDqWsDCaUtW5.SAHZJi5EJgtVZrHwO'," +
						"roles: [{authority : 'USER'}]," +
						"enabled: 'true'," +
						"reputation: 0," +
						"_class: 'com.mgmtp.radio.domain.user.User'}"

def insertUsersQuery = "db.user.insertMany( [ $userData_test, $userData_testuser1, $userData_testuser2 ] );"

def stationData_stationtest1 = "{_id : 'ObjectId(\"5ad5a3148b3a610004e46117\")'," +
								"    name : 'stationtest1'," +
								"    friendlyId : 'stationtest1'," +
								"    privacy : 'station_public'," +
								"    ownerId : '5adad567356be4000446af76'," +
								"    _class : 'com.mgmtp.radio.domain.station.Station'}"

def insertStationQuery = "db.station.insert($stationData_stationtest1);"

"$mongoPath\\mongo localhost:27017/new_radio_db_dev --eval \"$createCollectionsQuery\"".execute()
sleep(1000)

"$mongoPath\\mongo localhost:27017/new_radio_db_dev --eval \"$removeAllDataQuery\"".execute()
sleep(1000)

"$mongoPath\\mongo localhost:27017/new_radio_db_dev --eval \"$insertUsersQuery\"".execute()
sleep(1000)

"$mongoPath\\mongo localhost:27017/new_radio_db_dev --eval \"$insertStationQuery\"".execute()


