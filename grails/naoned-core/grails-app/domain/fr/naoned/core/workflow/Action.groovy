package fr.naoned.core.workflow

class Action {
	
	String name
	static hasMany = [states: State]

    static constraints = {
    }
}
