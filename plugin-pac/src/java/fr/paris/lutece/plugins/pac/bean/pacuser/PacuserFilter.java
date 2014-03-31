package fr.paris.lutece.plugins.pac.bean.pacuser;

import fr.paris.lutece.plugins.pac.bean.GenericJPAFilter;


/**
 * The Pacuser class for filter
 * @author jchaline
 */
public class PacuserFilter extends GenericJPAFilter<Integer>
{

    private static final long serialVersionUID = 721856727710348068L;
    private String guid;
    private String _nom;
    private String _prenom;
    private String _dayPresent;
    
    /**
     * @return the guid
     */
    public String getGuid( )
    {
        return guid;
    }
    /**
     * @param guid the guid to set
     */
    public void setGuid( String guid )
    {
        this.guid = guid;
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
     * @return the dayPresent
     */
    public String getDayPresent( )
    {
        return _dayPresent;
    }
    /**
     * @param dayPresent the dayPresent to set
     */
    public void setDayPresent( String dayPresent )
    {
        this._dayPresent = dayPresent;
    }
    
    

}