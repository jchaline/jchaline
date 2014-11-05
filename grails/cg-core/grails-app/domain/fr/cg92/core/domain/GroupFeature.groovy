package fr.cg92.core.domain

/**
 * Created by jchaline on 31/10/2014.
 */
class GroupFeature {

    String name
    String controllerName
    String actionName
    Integer orderSort

    static hasMany = [
        features:Feature
    ]

    static constraints = {
        name unique: true
        controllerName nullable:true
        actionName nullable:true
        orderSort nullable:true
    }

    String toString(){
        name
    }
}
