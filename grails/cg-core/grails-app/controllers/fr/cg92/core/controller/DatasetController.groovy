package fr.cg92.core.controller

import fr.cg92.core.domain.Feature
import fr.cg92.core.domain.GroupFeature

class DatasetController {

    def datasetService

    def index() {
        render "dataset work in progress !"
    }

    def generate(){
        datasetService.generateFeatures()
        redirect action:'index'
    }
}
