package scripts

class ScriptRunner {

	static clearStationList(){
		evaluate(new File('scripts/clear-station-list.groovy'))
	}

	static importStationtest1(){
		evaluate(new File('scripts/import-stationtest1.groovy'))
	}

	static importStationtest2(){
		evaluate(new File('scripts/import-stationtest2.groovy'))
	}
}
