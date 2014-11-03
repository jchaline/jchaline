package fr.cg92.core.domain

class SecRole {
    static String ADMINISTRATEUR = "ROLE_ADMINISTRATEUR"
    static String GESTIONNAIRE = "ROLE_GESTIONNAIRE"
    static String VISUALISEUR = "ROLE_VISUALISEUR"

    String authority
    String libelle

    static mapping = {
        cache true
    }

    static constraints = {
        authority blank: false, unique: true
        libelle nullable:true, unique:true
    }

    String toString(){
        libelle?:authority
    }
}
