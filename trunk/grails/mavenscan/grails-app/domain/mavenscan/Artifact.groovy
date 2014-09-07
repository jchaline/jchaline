package mavenscan

class Artifact {
	
	String artifactId
	String version
	String groupId
	String name
	
	

    static constraints = {
		artifactId()
		version()
		groupId()
    }
}
