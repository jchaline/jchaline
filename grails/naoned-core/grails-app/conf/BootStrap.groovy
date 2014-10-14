import fr.naoned.core.Feature
import fr.naoned.core.GroupFeature
import fr.naoned.core.security.SecRole
import fr.naoned.core.security.SecUser
import fr.naoned.core.security.SecUserSecRole

class BootStrap {

    def init = { servletContext ->
		
		initSecurity()
		
		initFeature()
    }
	
	def initFeature(){
		
    	GroupFeature gFeature = new GroupFeature(name:'Gestion des workflows',controllerName:'workflow').save()
		
		GroupFeature gAdmin = new GroupFeature(name:'Administration').save()
		gAdmin.addToFeatures(new Feature(name:'Feature',controllerName:'feature').save())
		gAdmin.addToFeatures(new Feature(name:'User',controllerName:'secUser').save())
		
		
		GroupFeature gFunctional = new GroupFeature(name:'Fonctionnel').save()
		gFunctional.addToFeatures(new Feature(name:"Cas d'utilisation 1",controllerName:'use', actionName:'use1').save())
		gFunctional.addToFeatures(new Feature(name:"Cas d'utilisation 2",controllerName:'case').save())
	}
	
	def initSecurity(){
		new SecRole(authority: 'ROLE_USER').save()
		
		def roleUser = new SecRole(authority: 'ROLE_ADMIN').save()
		def user = new SecUser(username: 'admin', password: 'admin', enabled: true).save()
		SecUserSecRole.create user, roleUser
	}
	
    def destroy = {
    }
}
