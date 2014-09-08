package mavenscan

class Dependency {

	static belongsTo = [owner: Artifact]
	
	String artifactId
	String groupId 
	String dependencyVersion
	
    static constraints = {
		groupId()
		artifactId()
		dependencyVersion()
    }
}
