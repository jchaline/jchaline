package fr.cg92.socle.plugin.imports.service

import fr.cg92.core.domain.GroupFeature
import fr.cg92.core.service.IDatasetService

/**
 * Created by jeremy on 06/11/2014.
 */
class ImportsDatasetService implements IDatasetService {

    def datasetService

    @Override
    void initData() {
        GroupFeature group = GroupFeature.findByName('Administration')?:new GroupFeature(name:'Administration',orderSort:1).save(failOnError: true)
        datasetService.initFeatures(group, [['Gestion des imports','import',1]])
    }

    @Override
    int level() {
        return 1
    }
}
