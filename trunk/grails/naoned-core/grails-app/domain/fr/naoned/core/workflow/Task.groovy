package fr.naoned.core.workflow

class Task {
	
	static hasMany = [states: State]

    static constraints = {
    }
}
