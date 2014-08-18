package fr.paris.lutece.plugins.pac.bean.pacuser;

import java.util.Date;

import fr.paris.lutece.plugins.genericjpa.bean.AbstractFilter;


/**
 * The Pacuser class for filter
 * @author jchaline
 */
public class PacuserFilter extends AbstractFilter<Integer>
{
    private static final long serialVersionUID = 721856727710348068L;
    private String guid;
    private String _strNom;
    private String _strPrenom;
    private Date _dayPresent;
    private Date _dayConge;
    
    @Override
    public String convertFieldName(String fieldName) {
    	if("nom".equals(fieldName)){
    		return "strNom";
    	}
    	else if("prenom".equals(fieldName)){
    		return "strPrenom";
    	}
    	else{
    		return fieldName;
    	}
    }

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
     * @return the dayPresent
     */
    public Date getDayPresent( )
    {
        return _dayPresent;
    }

    /**
     * @param dayPresent the dayPresent to set
     */
    public void setDayPresent( Date dayPresent )
    {
        this._dayPresent = dayPresent;
    }

    /**
     * @return the dayConge
     */
    public Date getDayConge( )
    {
        return _dayConge;
    }

    /**
     * @param dayConge the dayConge to set
     */
    public void setDayConge( Date dayConge )
    {
        this._dayConge = dayConge;
    }
}