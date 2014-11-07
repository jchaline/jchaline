package fr.cg92.core.service

import fr.cg92.core.domain.Feature
import fr.cg92.core.domain.GroupFeature
import grails.transaction.Transactional
import org.codehaus.groovy.grails.commons.GrailsApplication

@Transactional
class DatasetService implements IDatasetService {

    GrailsApplication grailsApplication

    void initIOC(){
        def listBeans = grailsApplication.mainContext.getBeansOfType(IDatasetService.class)
        listBeans.collect{e -> e.value}.sort{ it.level() }.each {
            it.initData()
        }

    }

    void initData(){
        //get all service with spring and call init for each
        GroupFeature group = GroupFeature.findByName('Administration')?:new GroupFeature(name:'Administration',orderSort:1).save(failOnError: true)
        initFeatures(group, [['Gestion des features','feature',1],['Gestion des groupes de features','groupFeature',2]])
    }

    void initFeatures(GroupFeature group, List featuresArray){
        featuresArray.each{
            if(!Feature.findByName(it[0])){
                Feature f = new Feature(name:it[0],controllerName:it[1], groupFeature: group, orderSort:it[2]).save(failOnError: true)
                group.addToFeatures(f)
            }
        }
        group.save(failOnError: true)
    }

    int level(){
        0
    }
}
