package fr.paris.lutece.plugins.pac.bean.pacdate;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import fr.paris.lutece.plugins.pac.bean.GenericJPABean;
import fr.paris.lutece.plugins.pac.bean.pacuser.Pacuser;


/**
 * the Pacdate bean
 * @author jchaline
 */
@Entity
@Table( name = "pac_date" )
public class Pacdate extends GenericJPABean<Integer>
{
    private static final long serialVersionUID = -6154572655913748217L;
    public static final String TABLE = "pac_date";

    @Id
    @TableGenerator( table = "pac_sequences", name = "pac_date_sequence", pkColumnValue = "pac_date_id", allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "pac_date_sequence" )
    @Column( name = "id" )
    private Integer _id;

    @Column( name = "type" )
    private String _type;

    @Column( name = "date" )
    private Date _date;

    @ManyToOne( fetch = FetchType.EAGER, cascade = { CascadeType.ALL } )
    @JoinColumn( name = "idOwner", nullable = false )
    private Pacuser _pacuser;

    /**
     * @return the date
     */
    public Date getDate( )
    {
        return _date;
    }

    /**
     * @param date the date to set
     */
    public void setDate( Date dateConge )
    {
        this._date = dateConge;
    }

    /**
     * @return the id
     */
    public Integer getId( )
    {
        return _id;
    }

    /**
     * @param id the id to set
     */
    public void setId( Integer id )
    {
        this._id = id;
    }

    /**
     * @return the type
     */
    public String getType( )
    {
        return _type;
    }

    /**
     * @param type the type to set
     */
    public void setType( String type )
    {
        this._type = type;
    }

    /**
     * @return the pacuser
     */
    public Pacuser getPacuser( )
    {
        return _pacuser;
    }

    /**
     * @param pacuser the pacuser to set
     */
    public void setPacuser( Pacuser pacuser )
    {
        this._pacuser = pacuser;
    }
}
