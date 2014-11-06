import fr.cg92.core.domain.SecRole
import fr.cg92.core.domain.SecUser
import fr.cg92.core.domain.SecUserSecRole

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
