package fr.naoned.core.workflow

class Action {
	
	String name
	State before
	State after

    static constraints = {
		name()
		before nullable:true
		after nullable:true
    }
}
