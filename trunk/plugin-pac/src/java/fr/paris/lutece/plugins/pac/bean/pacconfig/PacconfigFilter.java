package fr.paris.lutece.plugins.pac.bean.pacconfig;

import fr.paris.lutece.plugins.genericjpa.bean.AbstractFilter;


public class PacconfigFilter extends AbstractFilter<Integer>
{

    private static final long serialVersionUID = -85720468687553810L;

    private String _frequency;
    private String _team;
    
    /**
     * @return the frequency
     */
    public String getFrequency( )
    {
        return _frequency;
    }
    /**
     * @param frequency the frequency to set
     */
    public void setFrequency( String frequency )
    {
        this._frequency = frequency;
    }
    /**
     * @return the team
     */
    public String getTeam( )
    {
        return _team;
    }
    /**
     * @param team the team to set
     */
    public void setTeam( String team )
    {
        this._team = team;
    }
}
