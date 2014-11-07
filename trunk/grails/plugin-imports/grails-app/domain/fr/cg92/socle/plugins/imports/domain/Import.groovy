package fr.cg92.socle.plugins.imports.domain

/**
 * Created by jeremy on 06/11/2014.
 */
class Import {

    String name
    Date date

    static constraints = {
        name unique:true
    }
}
