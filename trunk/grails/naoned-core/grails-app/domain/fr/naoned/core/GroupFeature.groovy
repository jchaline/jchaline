package fr.naoned.core

class GroupFeature {
	
	String name
	static hasMany = [features: Feature]
	
    static constraints = {
		features nullable:true
    }
}
