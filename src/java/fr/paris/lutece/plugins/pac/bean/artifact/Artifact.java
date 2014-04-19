package fr.paris.lutece.plugins.pac.bean.artifact;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import fr.paris.lutece.plugins.pac.bean.GenericJPABean;
@Entity
@Table( name = "pac_artifact" )
public class Artifact extends GenericJPABean<Integer>
{
    private static final long serialVersionUID = -7285498025200549826L;
    
    @Id
    @TableGenerator( table = "pac_sequences", name = "pac_artifact_sequence", pkColumnValue = "pac_artifact_id", allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "pac_artifact_sequence" )
    @Column( name = "id" )
    private Integer _id;
    
    @Column(name="artifactId")
    private String _artifactId;
    @Column(name="groupId")
    private String _groupId;
    @Column(name="versionId")
    private String _version;
    
    @Override
    public Integer getId( )
    {
        return _id;
    }

    @Override
    public void setId( Integer key )
    {
        _id=key;
    }

    /**
     * @return the version
     */
    public String getVersion( )
    {
        return _version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion( String version )
    {
        this._version = version;
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

}
