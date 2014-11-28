import fr.cg92.core.domain.SecRole

grails.config.defaults.locations = [
        fr.cg92.core.config.CGDefaultConfig
]

envName = "${appName}-${grails.util.Environment.current.name}"
configPath = "${envName}-config.properties"
dbPath = "${envName}-db.properties"
println("Begin")
println("config : "+configPath)
println("db : "+dbPath)
println("end")

//grails.config.locations = ["classpath:${configPath}","classpath:db/${dbPath}"]

def projectRules = [
]

grails.plugin.springsecurity.controllerAnnotations.staticRules.putAll(projectRules)