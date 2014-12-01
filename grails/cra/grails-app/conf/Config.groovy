import fr.cg92.core.domain.SecRole

grails.config.defaults.locations = [
        fr.cg92.core.config.CGDefaultConfig
]

envName = "${appName}-${grails.util.Environment.current.name}"
grails.config.locations = ["file:${userHome}/conf/sograils/${appName}/${envName}-db.groovy",]

def projectRules = [
        '/collaborateur/**':                       [SecRole.ADMINISTRATEUR],
        '/projet/**':                              ['permitAll'],
]

grails.plugin.springsecurity.controllerAnnotations.staticRules.putAll(projectRules)