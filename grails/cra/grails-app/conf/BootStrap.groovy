import fr.cg92.core.domain.SecRole
import fr.cg92.core.domain.SecUser
import fr.cg92.core.domain.SecUserSecRole

class BootStrap {

    def init = { servletContext ->
        initSecurity()
    }

    /**
     * Initialisation de données de test pour la gestion de l'authentification
     */
    def initSecurity(){
        List listTuples = [["admin",SecRole.ADMINISTRATEUR,"Administrateur"],["gest",SecRole.GESTIONNAIRE,"Gestionnaire"],["visu",SecRole.VISUALISEUR,"Visualisateur"]]
        listTuples.each{
            SecRole r = SecRole.findByAuthority(it[1])?:new SecRole(authority:it[1], libelle:it[2]).save(failOnError: true)
            SecUser u = SecUser.findByUsername(it[0])
            if(!u){
                u = new SecUser(username: it[0], password: it[0], enabled: true, role:r).save(failOnError: true)
                SecUserSecRole.create u, r
            }
        }
    }

    def destroy = {
    }
}
