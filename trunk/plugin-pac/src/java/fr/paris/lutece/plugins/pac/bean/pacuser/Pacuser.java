package fr.paris.lutece.plugins.pac.bean.pacuser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import fr.paris.lutece.plugins.genericjpa.bean.AbstractBean;
import fr.paris.lutece.plugins.pac.bean.pacdate.Pacdate;


/**
 * The Pacuser Bean
 * @author jchaline
 */
@Entity
@Table( name = "pac_pacuser" )
public class Pacuser extends AbstractBean<Integer>
{
    private static final long serialVersionUID = -921977799714488740L;

    @Id
    @TableGenerator( table = "pac_sequences", name = "pac_pacuser_sequence", pkColumnValue = "pac_pacuser_id", allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "pac_pacuser_sequence" )
    @Column( name = "id" )
    private Integer _id;

    @Column( name = "guid" )
    private String _guid;

    @Column( name = "nom", nullable = false )
    @NotEmpty( message = "#i18n{portal.validation.message.notEmpty}" )
    private String _strNom;

    @Column( name = "prenom", nullable = false )
    @NotEmpty( message = "#i18n{portal.validation.message.notEmpty}" )
    private String _strPrenom;

    @Column( name = "email" )
    @Email
    private String _strEmail;

    @Column( name = "prochainPac" )
    private Date _prochainPac;

    @Column( name = "dateSortie" )
    private Date _dateSortie;

    @Column( name = "dateEntree" )
    @NotNull( message = "#i18n{portal.validation.message.notEmpty}" )
    private Date _dateEntree;

    @Column( name = "dernierPac" )
    private Date _dernierPac;

    @OneToMany( cascade = { CascadeType.ALL }, fetch = FetchType.EAGER, mappedBy = "_pacuser" )
    private List<Pacdate> _joursConges = new ArrayList<Pacdate>( 0 );

    @Override
    public int hashCode( )
    {
        return this.getId( ) * this.getNom( ).hashCode( ) * this.getPrenom( ).hashCode( );
    }

    @Override
    public boolean equals( final Object other )
    {
        boolean equal = false;
        if ( other != null && other instanceof Pacuser )
        {
            equal = this.getId( ).equals( ( (Pacuser) other ).getId( ) );
        }
        return equal;
    }

    /**
     * the id setter
     * @param id the id to set
     */
    @Override
    public void setId( Integer id )
    {
        this._id = id;
    }

    /**
     * the id getter
     * @return id
     */
    @Override
    public Integer getId( )
    {
        return this._id;
    }

    /**
     * the guid setter
     * @param guid the guid to set
     */
    public void setGuid( String guid )
    {
        this._guid = guid;
    }

    /**
     * the guid getter
     * @return guid
     */
    public String getGuid( )
    {
        return this._guid;
    }

    /**
     * @return the dateSortie
     */
    public Date getDateSortie( )
    {
        return _dateSortie;
    }

    /**
     * @param dateSortie the dateSortie to set
     */
    public void setDateSortie( Date dateSortie )
    {
        this._dateSortie = dateSortie;
    }

    /**
     * @return the dateEntree
     */
    public Date getDateEntree( )
    {
        return _dateEntree;
    }

    /**
     * @param dateEntree the dateEntree to set
     */
    public void setDateEntree( Date dateEntree )
    {
        this._dateEntree = dateEntree;
    }

    /**
     * @return the nom
     */
    public String getNom( )
    {
        return _strNom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom( String nom )
    {
        this._strNom = nom;
    }

    /**
     * @return the prenom
     */
    public String getPrenom( )
    {
        return _strPrenom;
    }

    /**
     * @param prenom the prenom to set
     */
    public void setPrenom( String prenom )
    {
        this._strPrenom = prenom;
    }

    /**
     * @return the dernierPac
     */
    public Date getDernierPac( )
    {
        return _dernierPac;
    }

    /**
     * @param dernierPac the dernierPac to set
     */
    public void setDernierPac( Date dernierPac )
    {
        this._dernierPac = dernierPac;
    }

    /**
     * @return the prochainPac
     */
    public Date getProchainPac( )
    {
        return _prochainPac;
    }

    /**
     * @param prochainPac the prochainPac to set
     */
    public void setProchainPac( Date prochainPac )
    {
        this._prochainPac = prochainPac;
    }

    /**
     * @return the joursConges
     */
    public List<Pacdate> getJoursConges( )
    {
        return _joursConges;
    }

    /**
     * @param joursConges the joursConges to set
     */
    public void setJoursConges( List<Pacdate> joursConges )
    {
        this._joursConges = joursConges;
    }

    /**
     * @return the email
     */
    public String getEmail( )
    {
        return _strEmail;
    }

    /**
     * @param email the email to set
     */
    public void setEmail( String email )
    {
        this._strEmail = email;
    }

    @Override
    public String toString( )
    {
        return _strPrenom + " " + _strNom;
    }
}