package fr.pacmaster.domain

class Pacuser {
	
	String lastName, firstName, email
	Date firstDay, lastPac, nextPac
	
	static hasMany = [holiday: Date]

    static constraints = {
		
    }
}
