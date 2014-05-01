package tools.mapplugins.xml;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "project")
public class Project implements Serializable
{
    private static final long serialVersionUID = -6333685159954247911L;

    @XmlElement(name="artifactid")
    private String artifactId;

    @XmlElement(name="version")
    private String version;

    @XmlElement(name="groupid")
    private String groupId;
    
    @XmlElement(name="type")
    private String type;
    
    @XmlElementWrapper(name="dependencies")
    @XmlElement(name="dependency")
    private List<Dependency> dependencies;
    
    /**
     * @return the groupId
     */
    public String getGroupId( )
    {
        return groupId;
    }
    /**
     * @param groupId the groupId to set
     */
    public void setGroupId( String groupId )
    {
        this.groupId = groupId;
    }
    /**
     * @return the artifactId
     */
    public String getArtifactId( )
    {
        return artifactId;
    }
    /**
     * @param artifactId the artifactId to set
     */
    public void setArtifactId( String artifactId )
    {
        this.artifactId = artifactId;
    }
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
    public List<Dependency> getDependencies( )
    {
        return dependencies;
    }
    /**
     * @param dependencies the dependencies to set
     */
    public void setDependencies( List<Dependency> dependencies )
    {
        this.dependencies = dependencies;
    }
    /**
     * @return the type
     */
    public String getType( )
    {
        return type;
    }
    /**
     * @param type the type to set
     */
    public void setType( String type )
    {
        this.type = type;
    }

}