package fr.naoned.core

class Feature {
	
	String name
	String controllerName
	String actionName

    static constraints = {
		name()
		controllerName()
		actionName nullable:true
    }
}
