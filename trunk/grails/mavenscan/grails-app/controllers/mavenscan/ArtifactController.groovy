package mavenscan



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ArtifactController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Artifact.list(params), model:[artifactInstanceCount: Artifact.count()]
    }

    def show(Artifact artifactInstance) {
        respond artifactInstance
    }

    def create() {
        respond new Artifact(params)
    }

    @Transactional
    def save(Artifact artifactInstance) {
        if (artifactInstance == null) {
            notFound()
            return
        }

        if (artifactInstance.hasErrors()) {
            respond artifactInstance.errors, view:'create'
            return
        }

        artifactInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'artifact.label', default: 'Artifact'), artifactInstance.id])
                redirect artifactInstance
            }
            '*' { respond artifactInstance, [status: CREATED] }
        }
    }

    def edit(Artifact artifactInstance) {
        respond artifactInstance
    }

    @Transactional
    def update(Artifact artifactInstance) {
        if (artifactInstance == null) {
            notFound()
            return
        }

        if (artifactInstance.hasErrors()) {
            respond artifactInstance.errors, view:'edit'
            return
        }

        artifactInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Artifact.label', default: 'Artifact'), artifactInstance.id])
                redirect artifactInstance
            }
            '*'{ respond artifactInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Artifact artifactInstance) {

        if (artifactInstance == null) {
            notFound()
            return
        }

        artifactInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Artifact.label', default: 'Artifact'), artifactInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'artifact.label', default: 'Artifact'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
