package fr.cg92.core.controller

import fr.cg92.core.domain.Feature
import fr.cg92.core.domain.GroupFeature
import fr.cg92.core.service.DatasetService

class DatasetController {

    def datasetService

    def index() {
        render "dataset work in progress !"
    }

    def generate(){
        datasetService.initFeatures()
        redirect action:'index'
    }
}
