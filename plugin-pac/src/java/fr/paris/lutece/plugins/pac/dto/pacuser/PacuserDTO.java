package fr.paris.lutece.plugins.pac.dto.pacuser;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import fr.paris.lutece.plugins.genericjpa.dto.AbstractDTO;
import fr.paris.lutece.plugins.pac.bean.pacdate.Pacdate;
import fr.paris.lutece.plugins.pac.bean.pacuser.Pacuser;


/**
 * The Pacuser class DTO
 * @author jchaline
 */
public class PacuserDTO extends AbstractDTO<Integer, Pacuser>
{
    private Integer _id;
    private String _guid;
    @NotEmpty
    private String _nom;
    @NotEmpty
    private String _prenom;
    @NotEmpty
    private String _email;
    @NotEmpty
    private String _dateEntree = "";
    private String _dateSortie = "";
    private String _dernierPac = "";
    private String _prochainPac = "";
    private String _joursConges = "";

    public void setId( Integer id )
    {
        this._id = id;
    }

    public Integer getId( )
    {
        return this._id;
    }

    public void setGuid( String guid )
    {
        this._guid = guid;
    }

    public String getGuid( )
    {
        return this._guid;
    }

    @Override
    public Pacuser convert( )
    {
        Pacuser target = _dozerMapper.map( this, Pacuser.class );

        if ( target.getJoursConges( ) != null )
        {
            for ( Pacdate date : target.getJoursConges( ) )
            {
                date.setPacuser( target );
            }
        }
        return target;
    }

    /**
     * Convert bean into dto
     * @param bean the bean to convert
     * @return the dto converted
     */
    public static PacuserDTO convert( Pacuser bean )
    {
        return _dozerMapper.map( bean, PacuserDTO.class );
    }

    /**
     * Convert bean list into dto list
     * @param listPacuser the list to convert
     * @return the dot list converted
     */
    public static List<PacuserDTO> convert( List<Pacuser> listPacuser )
    {
        List<PacuserDTO> list = new ArrayList<PacuserDTO>( );
        for ( Pacuser pacuser : listPacuser )
        {
            list.add( _dozerMapper.map( pacuser, PacuserDTO.class ) );
        }
        return list;
    }

    /**
     * @return the nom
     */
    public String getNom( )
    {
        return _nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom( String nom )
    {
        this._nom = nom;
    }

    /**
     * @return the prenom
     */
    public String getPrenom( )
    {
        return _prenom;
    }

    /**
     * @param prenom the prenom to set
     */
    public void setPrenom( String prenom )
    {
        this._prenom = prenom;
    }

    /**
     * @return the dateEntree
     */
    public String getDateEntree( )
    {
        return _dateEntree;
    }

    /**
     * @param dateEntree the dateEntree to set
     */
    public void setDateEntree( String dateEntree )
    {
        this._dateEntree = dateEntree;
    }

    /**
     * @return the dateSortie
     */
    public String getDateSortie( )
    {
        return _dateSortie;
    }

    /**
     * @param dateSortie the dateSortie to set
     */
    public void setDateSortie( String dateSortie )
    {
        this._dateSortie = dateSortie;
    }

    /**
     * @return the dernierPac
     */
    public String getDernierPac( )
    {
        return _dernierPac;
    }

    /**
     * @param dernierPac the dernierPac to set
     */
    public void setDernierPac( String dernierPac )
    {
        this._dernierPac = dernierPac != null ? dernierPac : "";
    }

    /**
     * @return the prochainPac
     */
    public String getProchainPac( )
    {
        return _prochainPac;
    }

    /**
     * @param prochainPac the prochainPac to set
     */
    public void setProchainPac( String prochainPac )
    {
        this._prochainPac = prochainPac != null ? prochainPac : "";
    }

    /**
     * @return the joursConges
     */
    public String getJoursConges( )
    {
        return _joursConges;
    }

    /**
     * @param joursConges the joursConges to set
     */
    public void setJoursConges( String joursConges )
    {
        this._joursConges = joursConges;
    }

    /**
     * @return the email
     */
    public String getEmail( )
    {
        return _email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail( String email )
    {
        this._email = email;
    }
}