class BootStrap {

    def datasetService
    def securityService

    def init = { servletContext ->
        datasetService.initIOC()
        securityService.initSecurity()
    }

    def destroy = {
    }
}
