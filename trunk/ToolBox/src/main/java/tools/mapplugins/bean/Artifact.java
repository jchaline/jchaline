package tools.mapplugins.bean;


public class Artifact extends ArtifactGroup
{
    protected String id;
    
    /**
     * @return the id
     */
    public String getId( )
    {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId( String id )
    {
        this.id = id;
    }
}
