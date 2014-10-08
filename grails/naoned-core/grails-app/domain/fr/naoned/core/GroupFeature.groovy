package fr.naoned.core

class GroupFeature {
	
	String name
	String controllerName
	String actionName
	static hasMany = [features: Feature]
	
    static constraints = {
		controllerName nullable:true
		actionName nullable:true
		features nullable:true
    }
}
