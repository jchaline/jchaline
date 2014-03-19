package fr.paris.lutece.plugins.pac.bean.artifact;

import fr.paris.lutece.plugins.pac.bean.GenericJPAFilter;

public class ArtifactFilter extends GenericJPAFilter<Integer>
{
    private static final long serialVersionUID = 8497560292642310890L;
    
    private String _artifactId;
    private String _groupId;
    private String _versionId;
    /**
     * @return the artifactId
     */
    public String getArtifactId( )
    {
        return _artifactId;
    }
    /**
     * @param artifactId the artifactId to set
     */
    public void setArtifactId( String artifactId )
    {
        this._artifactId = artifactId;
    }
    /**
     * @return the groupId
     */
    public String getGroupId( )
    {
        return _groupId;
    }
    /**
     * @param groupId the groupId to set
     */
    public void setGroupId( String groupId )
    {
        this._groupId = groupId;
    }
    /**
     * @return the versionId
     */
    public String getVersionId( )
    {
        return _versionId;
    }
    /**
     * @param versionId the versionId to set
     */
    public void setVersionId( String versionId )
    {
        this._versionId = versionId;
    }

}
