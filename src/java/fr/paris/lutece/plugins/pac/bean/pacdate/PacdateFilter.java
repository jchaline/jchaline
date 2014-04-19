package fr.paris.lutece.plugins.pac.bean.pacdate;

import java.util.Date;

import fr.paris.lutece.plugins.pac.bean.GenericJPAFilter;

public class PacdateFilter extends GenericJPAFilter<Integer>
{

    private static final long serialVersionUID = -85720468687553810L;
    
    private Integer _idOwner;
    private String _type;
    private Date _dateMin;
    private Date _dateMax;
    /**
     * @return the idOwner
     */
    public Integer getIdOwner( )
    {
        return _idOwner;
    }
    /**
     * @param idOwner the idUser to set
     */
    public void setIdOwner( Integer idOwner )
    {
        this._idOwner = idOwner;
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
     * @return the dateMin
     */
    public Date getDateMin( )
    {
        return _dateMin;
    }
    /**
     * @param dateMin the dateMin to set
     */
    public void setDateMin( Date dateMin )
    {
        this._dateMin = dateMin;
    }
    /**
     * @return the dateMax
     */
    public Date getDateMax( )
    {
        return _dateMax;
    }
    /**
     * @param dateMax the dateMax to set
     */
    public void setDateMax( Date dateMax )
    {
        this._dateMax = dateMax;
    }

}
