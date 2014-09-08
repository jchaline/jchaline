package mavenscan

class Artifact {
	
	static hasMany = [dependencies: Dependency]
	
	String artifactId, groupId, artifactVersion

    static constraints = {
		groupId()
		artifactId()
		artifactVersion()
    }
}
