package mavenscan



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class DependencyController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Dependency.list(params), model:[dependencyInstanceCount: Dependency.count()]
    }

    def show(Dependency dependencyInstance) {
        respond dependencyInstance
    }

    def create() {
        respond new Dependency(params)
    }

    @Transactional
    def save(Dependency dependencyInstance) {
        if (dependencyInstance == null) {
            notFound()
            return
        }

        if (dependencyInstance.hasErrors()) {
            respond dependencyInstance.errors, view:'create'
            return
        }

        dependencyInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'dependency.label', default: 'Dependency'), dependencyInstance.id])
                redirect dependencyInstance
            }
            '*' { respond dependencyInstance, [status: CREATED] }
        }
    }

    def edit(Dependency dependencyInstance) {
        respond dependencyInstance
    }

    @Transactional
    def update(Dependency dependencyInstance) {
        if (dependencyInstance == null) {
            notFound()
            return
        }

        if (dependencyInstance.hasErrors()) {
            respond dependencyInstance.errors, view:'edit'
            return
        }

        dependencyInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Dependency.label', default: 'Dependency'), dependencyInstance.id])
                redirect dependencyInstance
            }
            '*'{ respond dependencyInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Dependency dependencyInstance) {

        if (dependencyInstance == null) {
            notFound()
            return
        }

        dependencyInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Dependency.label', default: 'Dependency'), dependencyInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'dependency.label', default: 'Dependency'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
