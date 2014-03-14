package tools.mapplugins.bean;

import java.util.List;

/**
 * Add version to the artifact
 * @author jchaline
 *
 */
public class ArtifactVersion extends Artifact
{
    protected String version;
    private List<ArtifactVersion> dependencies;

    /**
     * @return the version
     */
    public String getVersion( )
    {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion( String version )
    {
        this.version = version;
    }

    /**
     * @return the dependencies
     */
    public List<ArtifactVersion> getDependencies( )
    {
        return dependencies;
    }

    /**
     * @param dependencies the dependencies to set
     */
    public void setDependencies( List<ArtifactVersion> dependencies )
    {
        this.dependencies = dependencies;
    }

}
