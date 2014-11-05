package fr.cg92.core.service

import fr.cg92.core.domain.Feature
import fr.cg92.core.domain.GroupFeature
import grails.transaction.Transactional

@Transactional
class DatasetService {

    def generateFeatures() {
        Feature.list().each{it.delete()}
        GroupFeature.list().each{it.delete()}
        GroupFeature g1 = new GroupFeature(name:'Administration',orderSort:1).save(failOnError: true)
        Feature f1 = new Feature(name:'Gestion des features',controllerName:'feature', groupFeature: g1, orderSort:1).save(failOnError: true)
        Feature f2 = new Feature(name:'Gestion des groupes features',controllerName:'groupFeature', groupFeature: g1, orderSort:2).save(failOnError: true)
        g1.addToFeatures(f1)
        g1.addToFeatures(f2)
        g1.save(failOnError: true)
    }
}
