import fr.cg92.core.domain.SecRole

grails.config.defaults.locations = [
        fr.cg92.core.config.CGDefaultConfig
]


// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

def projectRules = [
        '/collaborateur/**':                              [SecRole.ADMINISTRATEUR],
        '/projet/**':                              ['permitAll'],
]
grails.plugin.springsecurity.controllerAnnotations.staticRules.putAll(projectRules)