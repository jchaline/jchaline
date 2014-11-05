package fr.cg92.core.domain

/**
 * Created by jchaline on 31/10/2014.
 */
class Feature {
    String name
    String controllerName
    String actionName
    Integer orderSort
    boolean visible=true

    static belongsTo = [groupFeature: GroupFeature]

    static constraints = {
        name unique: true
        controllerName nullable:false
        actionName nullable:true
        orderSort nullable:true
    }

    String toString(){
        name
    }
}
