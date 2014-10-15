package fr.naoned.core.workflow

class State {
	String name
	
	static hasMany = [tasks:Task]

    static constraints = {
		name()
		tasks()
    }
	
	String toString(){
		name
	}
}
