package tools.mapplugins.bean;

import java.util.Map;

/**
 * Handling artifacts
 * @author jchaline
 */
public class Repository
{
    /**
     * Reference all the artifact, getting by group id, id, and version
     */
    private Map<String, Map<String, Map<String,Artifact>>> artifacts;

    /**
     * @return the artifacts
     */
    public Map<String, Map<String, Map<String,Artifact>>> getArtifacts( )
    {
        return artifacts;
    }

    /**
     * @param artifacts the artifacts to set
     */
    public void setArtifacts( Map<String, Map<String, Map<String,Artifact>>> artifacts )
    {
        this.artifacts = artifacts;
    }
}
