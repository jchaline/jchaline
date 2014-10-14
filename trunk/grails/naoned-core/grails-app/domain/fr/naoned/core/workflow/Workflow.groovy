package fr.naoned.core.workflow

class Workflow {
	
	String name
	static hasMany = [actions: Action]

    static constraints = {
    }
}
