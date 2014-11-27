grails.servlet.version = "3.0" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.work.dir = "target/work"
grails.project.target.level = 1.7
grails.project.source.level = 1.7
grails.project.war.file = "target/${appName}.war"

forkConfig = false
grails.project.fork = [
    test: forkConfig,
    run: forkConfig,
    war: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    console: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256]
]

grails.project.dependency.resolver = "maven" // or ivy
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
    }
    log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve
    legacyResolve false // whether to do a secondary resolve on plugin installation, not advised and here for backwards compatibility

    repositories {
        inherits true // Whether to inherit repository definitions from plugins

        grailsPlugins()
        grailsHome()
        mavenLocal()
        grailsCentral()
        mavenCentral()
    }

    dependencies {
        runtime 'mysql:mysql-connector-java:5.1.29'
        test "org.grails:grails-datastore-test-support:1.0-grails-2.4"
    }

    plugins {
        build ":tomcat:7.0.55"

        compile "fr.cg92.socle:cg-core:1.0.0-SNAPSHOT"
        compile "fr.cg92.socle.plugins:plugin-imports:1.0.0-SNAPSHOT"
    }
}
grails.assets.minifyJs = false

grails.plugin.location.'cg-core' = "../cg-core"
grails.plugin.location.'plugin-imports' = "../plugin-imports"
