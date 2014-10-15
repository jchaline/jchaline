package fr.naoned.core.workflow

class Workflow {
	
	String name
	State initial
	State current
	static hasMany = [actions: Action]

    static constraints = {
		name()
		current nullable:false
		initial nullable:false
		actions()
    }
	
	String toString(){
		name
	}
}
